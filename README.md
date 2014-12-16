# Project Frankenstein

The Frankenweenie experiments that Victor tried to keep secret!

## The Laboratory

TBD

## Sparky

* Clone, build and (locally) publish `spore-core`:
    * `git clone https://github.com/carlpulley/spores.git`
    * `cd spores`
    * `git checkout research`
    * `sbt compile`
    * `sbt ";project spores-core ;test"` (**TODO:** get tests compiling for `spores-pickling`)
    * `sbt publishLocal`
* Ensure that Apache Spark is installed:
    * `brew install apache-spark`
* Build Sparky and his libraries:
    * `sbt compile`
    * `sbt stage`

### Experiment 1

Simple PoC demonstration to validate that our setup is sane:
* in terminal 1 run:
    * `nc -lk 9999`
* in terminal 2 run:
    * ```spark-submit --class cakesolutions.example1.Main --master local[4] --jars `ls -m ./sparky/target/universal/stage/lib/*.jar | tr -d " \n"` ./sparky/target/universal/stage/lib/cakesolutions.sparky-0.1.0-SNAPSHOT.jar localhost 9999```

Pasting multi-line text in the terminal 1 window should cause word counts to be displayed (amongst the Spark logging output)
in terminal 2.

### Experiment 2

Simple PoC demonstration to validate that Spark workflows can be defined using Spores (think quasiquoting):
* in terminal 1 run:
    * `nc -lk 9999`
* in terminal 2 run:
    * ```spark-submit --class cakesolutions.example2.Main --master local[4] --jars `ls -m ./sparky/target/universal/stage/lib/*.jar | tr -d " \n"` ./sparky/target/universal/stage/lib/cakesolutions.sparky-0.1.0-SNAPSHOT.jar localhost 9999```

Pasting multi-line text in the terminal 1 window should cause word counts to be displayed (amongst the Spark logging output)
in terminal 2.

### Experiment 3

Simple PoC demonstration to validate that Spark workflows can be pickled/serialised and then, after storage of serialised
Spark workflow to a file, correctly unpickled/de-serialised:
* in terminal 1 run:
    * `nc -lk 9999`
* in terminal 2 run:
    * ```spark-submit --class cakesolutions.example3.Main --master local[4] --jars `ls -m ./sparky/target/universal/stage/lib/*.jar | tr -d " \n"` ./sparky/target/universal/stage/lib/cakesolutions.sparky-0.1.0-SNAPSHOT.jar /tmp/spark.workflow localhost 9999```

Pasting multi-line text in the terminal 1 window should cause word counts to be displayed (amongst the Spark logging output)
in terminal 2.
