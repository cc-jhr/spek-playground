package io.github.spekplayground.executionorder

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SpekExecutionOrder : Spek({
    val log: Logger = LoggerFactory.getLogger(SpekExecutionOrder::class.java)

    log.info("body Spec")

    beforeEachTest {
        log.info("beforeEachTest Spec")
    }

    afterEachTest {
        log.info("afterEachTest Spec")
    }

    given("Group-A") {
        log.info("  given Group-A")

        beforeGroup {
            log.info("    beforeGroup Group-A")
        }

        afterGroup {
            log.info("    afterGroup Group-A")
        }

        beforeEachTest {
            log.info("    beforeEachTest Group-A")
        }

        afterEachTest {
            log.info("    afterEachTest Group-A")
        }

        context("Context-1 in Group-A") {
            log.info("      context Context-1")

            beforeGroup {
                log.info("        beforeGroup Context-1")
            }

            afterGroup {
                log.info("        afterGroup Context-1")
            }

            beforeEachTest {
                log.info("        beforeEachTest Context-1")
            }

            afterEachTest {
                log.info("        afterEachTest Context-1")
            }

            it("Context-1 it") {
                log.info("        it Context-1")
            }
        }

        context("Context-2") {
            log.info("      context Context-2")

            beforeGroup {
                log.info("        beforeGroup Context-2")
            }

            afterGroup {
                log.info("        afterGroup Context-2")
            }

            beforeEachTest {
                log.info("        beforeEachTest Context-2")
            }

            afterEachTest {
                log.info("        afterEachTest Context-2")
            }

            it("Context-2 it") {
                log.info("        it Context-2")
            }
        }

        on("Test-1") {
            log.info("      on Test-1")

            it("Assertion-1") {
                log.info("        it Test-1")
            }
        }

        on("Test-2") {
            log.info("      on Test-2")

            it("Assertion-2") {
                log.info("        it Test-2")
            }
        }
    }

    given("Group-B") {
        log.info("  given Group-B")

        beforeGroup {
            log.info("    beforeGroup Group-B")
        }

        afterGroup {
            log.info("    afterGroup Group-B")
        }

        beforeEachTest {
            log.info("    beforeEachTest Group-B")
        }

        afterEachTest {
            log.info("    afterEachTest Group-B")
        }

        on("Test-3") {
            log.info("    on Test-3")

            it("Assertion-3") {
                log.info("      it Test-3")
            }
        }

        on("Test-4") {
            log.info("    on Test-4")

            it("Assertion-4") {
                log.info("      it Test-4")
            }
        }
    }
})