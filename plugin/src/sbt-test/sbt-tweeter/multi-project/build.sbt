lazy val root = (project in file(".")).aggregate(projectA, projectB)

lazy val projectA = (project in file("a"))

lazy val projectB = (project in file("b")).dependsOn(projectA)
