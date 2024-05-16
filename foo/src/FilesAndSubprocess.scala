package foo

object FilesAndSubprocess {
  def main(args: Array[String]): Unit = {

    val url = "https://api.github.com/repos/lihaoyi/mill/releases"
    os.proc("curl", url).call(stdout = os.pwd / "github-releases.json")

    val sub = os
      .proc("python", "-u", "-c", "while True: print(eval(raw_input()))")
      .spawn()
    sub.stdin.writeLine("1 + 2 + 3")
    sub.stdin.flush()
    sub.stdout.readLine()

    val download = os
      .proc("curl", "https://api.github.com/repos/lihaoyi/mill/releases")
      .spawn()
    val base64 = os.proc("base64").spawn(stdin = download.stdout)
    val gzip = os.proc("gzip").spawn(stdin = base64.stdout)
    val tee = os.proc("tee", "base64.gz").spawn(stdin = gzip.stdout)
    val upload = os
      .proc("curl", "-X", "PUT", "-d", "@-", "https://httpbin.org/anything")
      .spawn(stdin = tee.stdout)

    val contentLength = upload.stdout.lines.filter(_.contains("Content-Length"))

    def syn(src: os.Path, dest: os.Path) = {
      for (srcSubPath <- os.walk(src)) {
        val subPath = srcSubPath.subRelativeTo(src)
        val destSubPath = dest / subPath
        //  println((os.isDir(srcSubPath), os.isDir(destSubPath)))
        (os.isDir(srcSubPath), os.isDir(destSubPath)) match {
          case (true, false) | (false, true) =>
            os.copy.over(srcSubPath, destSubPath, createFolders = true)
          case (false, false) =>
            if (
              !os.exists(destSubPath) || !os.read
                .bytes(srcSubPath)
                .sameElements(os.read.bytes(destSubPath))
            ) { os.copy.over(srcSubPath, destSubPath, createFolders = true) }

          case _ => // do nothing
        }
      }

    }
  }
}
