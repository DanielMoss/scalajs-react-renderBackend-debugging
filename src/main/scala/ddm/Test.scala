package ddm

import japgolly.scalajs.react.component.Scala.{BackendScope, Component}
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{CtorType, ScalaComponent}

import scala.language.implicitConversions

object Test {
  /* Successfully compiles in both Scala 2.13 and Scala 3.0 */
  def buildV1[N : Numeric]: Component[N, N, Backend[N], CtorType.Props] =
    ScalaComponent
      .builder[N]
      .initialState(Numeric[N].zero)
      .backend(new Backend(_))
      .renderBackend
      .build

  /* Successfully compiles in Scala 2.13, but fails in Scala 3.0
   * [error] 30 |    ScalaComponent
   * [error] 31 |      .builder[N]
   * [error] 32 |      .initialState(Numeric[N].zero)
   * [error] 33 |      .renderBackend[Backend[N]]
   * [error]    |    ^
   * [error]    |    no implicit values were found that match type Numeric[N]
   * [error]    | This location contains code that was inlined from Test.scala:14
   * [error]    | This location contains code that was inlined from ComponentBuilder.scala:145
   */
  def buildV2[N : Numeric]: Component[N, N, Backend[N], CtorType.Props] =
    ScalaComponent
      .builder[N]
      .initialState(Numeric[N].zero)
      .renderBackend[Backend[N]]
      .build

  final class Backend[N : Numeric](scope: BackendScope[N, N]) {
    def render(props: N, state: N): VdomNode =
      ???
  }
}
