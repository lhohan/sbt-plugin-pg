import java.io.File
import sbt._
import Keys._
import scala.{Option, None}

object Plugins extends Build {

  // issue on Windows the git chek out dir is not automatically created, see:
  // https://github.com/sbt/sbt/issues/895


  // Return our new resolver by default
  override def buildLoaders =
    BuildLoader.resolve(gitResolver) ::
      Nil

  // Define a new build resolver to wrap the original git one
  def gitResolver(info: BuildLoader.ResolveInfo): Option[() => File] =
    if (info.uri.getScheme != "git")
      None
    else {
      // Use a subdirectory of the staging directory for the new plugin build.
      // The subdirectory name is derived from a hash of the URI,
      // and so identical URIs will resolve to the same directory.
      val hashDir = new File(info.staging, Hash.halfHashString(info.uri.normalize.toASCIIString))
      hashDir.mkdirs()

      // Return the original git resolver that will do the actual work.
      Resolvers.git(info)
    }
}