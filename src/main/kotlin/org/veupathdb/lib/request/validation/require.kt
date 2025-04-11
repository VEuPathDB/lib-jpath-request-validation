package org.veupathdb.lib.request.validation

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * Ensures that the target value is not `null` before calling the provided
 * validation function.
 *
 * If the target value is `null`, an error will be recorded, and the provided
 * validation function will not be executed.
 *
 * @receiver Value to test.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param checks Validation function to execute on the target value if it is not
 * `null`.
 *
 * @since 0.1.0
 */
@OptIn(ExperimentalContracts::class)
inline fun <T> T?.require(jPath: String, errors: ValidationErrors, checks: T.() -> Unit) {
  contract { callsInPlace(checks, InvocationKind.AT_MOST_ONCE) }
  this?.checks() ?: errors.add(jPath, messageIndex.nullErrorMessage)
}

/**
 * Ensures that the target value is not `null` before calling the provided
 * validation function.
 *
 * If the target value is `null`, an error will be recorded, and the provided
 * validation function will not be executed.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Value to test.
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
 * @param checks Validation function to execute on the target value if it is not
 * `null`.
 *
 * @since 0.1.0
 */
@OptIn(ExperimentalContracts::class)
inline fun <T> T?.require(jPath: String, index: Int, errors: ValidationErrors, checks: T.() -> Unit) {
  contract { callsInPlace(checks, InvocationKind.AT_MOST_ONCE) }
  this?.checks() ?: errors.add(jPath..index, messageIndex.nullErrorMessage)
}

