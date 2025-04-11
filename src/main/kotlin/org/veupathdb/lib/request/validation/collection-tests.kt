@file:JvmMultifileClass
@file:JvmName("Validation")
package org.veupathdb.lib.request.validation

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Requires that the target collection is not `null` or empty.
 *
 * @receiver Target collection to test.
 *
 * @param T Type of value contained in the target collection.
 *
 * @param C Type of the collection being tested.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @since 0.1.0
 */
@OptIn(ExperimentalContracts::class)
inline fun <T, C: Collection<T>> C?.requireNonEmpty(jPath: String, errors: ValidationErrors, checks: C.() -> Unit) {
  contract { returns(true) implies (this@requireNonEmpty != null) }
  when {
    this == null -> errors.add(jPath, messageIndex.nullErrorMessage)
    isEmpty()    -> errors.add(jPath, messageIndex.emptyErrorMessage)
    else         -> checks()
  }
}

/**
 * Requires that the target collection is not `null` or empty.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Target collection to test.
 *
 * @param T Type of value contained in the target collection.
 *
 * @param C Type of the collection being tested.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param index Index of the target string in a parent collection.
 *
 * This value will be added to the JSON path when recording any validation
 * errors.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @since 0.1.0
 */
@OptIn(ExperimentalContracts::class)
inline fun <T, C: Collection<T>> C?.requireNonEmpty(jPath: String, index: Int, errors: ValidationErrors, checks: C.() -> Unit) {
  contract { returns(true) implies (this@requireNonEmpty != null) }
  when {
    this == null -> errors.add(jPath..index, messageIndex.nullErrorMessage)
    isEmpty()    -> errors.add(jPath..index, messageIndex.emptyErrorMessage)
    else         -> checks()
  }
}
