# Reproducer for the Amazon ELB/chunked response bug

This is a reproducer for the bug described [in the gatling forum](https://groups.google.com/forum/#!searchin/gatling/subject$3AELB|sort:relevance/gatling/jNAw1hj776I/E_sGfh6SAwAJ)

# Run

     mvn -nsu test gatling:execute -Dgatling.simulationClass=org.nuxeo.cap.bench.Sim00Setup -Pbench  -Durl=<TARGET_ELB_URL>

# About Nuxeo

Nuxeo dramatically improves how content-based applications are built, managed and deployed, making customers more agile, innovative and successful. Nuxeo provides a next generation, enterprise ready platform for building traditional and cutting-edge content oriented applications. Combining a powerful application development environment with
SaaS-based tools and a modular architecture, the Nuxeo Platform and Products provide clear business value to some of the most recognizable brands including Verizon, Electronic Arts, Netflix, Sharp, FICO, the U.S. Navy, and Boeing. Nuxeo is headquartered in New York and Paris.
More information is available at [www.nuxeo.com](http://www.nuxeo.com).


