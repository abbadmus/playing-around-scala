package foo
import utest._
object FooTests extends TestSuite {
  def tests = Tests {
    test("hello") {
      val result = Example.hello()
      assert(result ==  "hello world")
      result
    }
    
  }
}
