logLevel := Level.Warn

//
//lazy val mySbtPlugin = uri("git://github.com/lhohan/sbt-plugin#v0.1.0")
//
//lazy val plugins = Project("plugins", file(".")).dependsOn(mySbtPlugin)

addSbtPlugin("eu.lhoest" %% "sbt-plugin" % "0.2-SNAPSHOT")