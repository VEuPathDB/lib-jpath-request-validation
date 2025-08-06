@file:JvmMultifileClass
@file:JvmName("Validation")
@file:Suppress("NOTHING_TO_INLINE")
package org.veupathdb.lib.request.validation

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

// region Non Null

/**
 * Validates that the target value is greater than or equal to the given minimum
 * value.
 *
 * @receiver Target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMaximum
 * @see checkInRange
 *
 * @since 0.2.0
 */
inline fun Short.checkMinimum(jPath: String, min: Short, errors: ValidationErrors): Boolean {
  if (this < min) {
    errors.add(jPath, messageIndex.minValueErrorMessage(min, this))
    return false
  }

  return true
}

/**
 * Validates that the target value is greater than or equal to the given minimum
 * value.
 *
 * @receiver Target short to validate.
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
 * @param min Minimum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMaximum
 * @see checkInRange
 *
 * @since 0.2.0
 */
inline fun Short.checkMinimum(jPath: String, index: Int, min: Short, errors: ValidationErrors): Boolean {
  if (this < min) {
    errors.add(jPath..index, messageIndex.minValueErrorMessage(min, this))
    return false
  }

  return true
}

/**
 * Validates that the target value is less than or equal to the given maximum
 * value.
 *
 * @receiver Target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMinimum
 * @see checkInRange
 *
 * @since 0.2.0
 */
inline fun Short.checkMaximum(jPath: String, max: Short, errors: ValidationErrors): Boolean {
  if (this > max) {
    errors.add(jPath, messageIndex.maxValueErrorMessage(max, this))
    return false
  }

  return true
}

/**
 * Validates that the target value is less than or equal to the given maximum
 * value.
 *
 * @receiver Target short to validate.
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
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMinimum
 * @see checkInRange
 *
 * @since 0.2.0
 */
inline fun Short.checkMaximum(jPath: String, index: Int, max: Short, errors: ValidationErrors): Boolean {
  if (this > max) {
    errors.add(jPath..index, messageIndex.maxValueErrorMessage(max, this))
    return false
  }

  return true
}

/**
 * Validates that the target value is within the inclusive valid range.
 *
 * @receiver Target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid value.
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMinimum
 * @see checkMaximum
 *
 * @since 0.2.0
 */
inline fun Short.checkInRange(jPath: String, min: Short, max: Short, errors: ValidationErrors) =
  checkMinimum(jPath, min, errors) && checkMaximum(jPath, max, errors)

/**
 * Validates that the target value is within the inclusive valid range.
 *
 * @receiver Target short to validate.
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
 * @param min Minimum valid value.
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMinimum
 * @see checkMaximum
 *
 * @since 0.2.0
 */
inline fun Short.checkInRange(jPath: String, index: Int, min: Short, max: Short, errors: ValidationErrors) =
  checkMinimum(jPath, index, min, errors) && checkMaximum(jPath, index, max, errors)

// endregion Non Null

// region Optional

/**
 * Validates that the optional target value is greater than or equal to the
 * given minimum value if present
 *
 * @receiver Optional target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMaximum
 * @see optCheckInRange
 *
 * @since 0.2.0
 */
inline fun Short?.optCheckMinimum(jPath: String, min: Short, errors: ValidationErrors) =
  this?.checkMinimum(jPath, min, errors) ?: true

/**
 * Validates that the optional target value is greater than or equal to the
 * given minimum value if present
 *
 * @receiver Optional target short to validate.
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
 * @param min Minimum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMaximum
 * @see optCheckInRange
 *
 * @since 0.2.0
 */
inline fun Short?.optCheckMinimum(jPath: String, index: Int, min: Short, errors: ValidationErrors) =
  this?.checkMinimum(jPath, index, min, errors) ?: true

/**
 * Validates that the optional target value is less than or equal to the
 * given maximum value if present
 *
 * @receiver Optional target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinimum
 * @see optCheckInRange
 *
 * @since 0.2.0
 */
inline fun Short?.optCheckMaximum(jPath: String, max: Short, errors: ValidationErrors) =
  this?.checkMaximum(jPath, max, errors) ?: true

/**
 * Validates that the optional target value is less than or equal to the
 * given maximum value if present
 *
 * @receiver Optional target short to validate.
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
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinimum
 * @see optCheckInRange
 *
 * @since 0.2.0
 */
inline fun Short?.optCheckMaximum(jPath: String, index: Int, max: Short, errors: ValidationErrors) =
  this?.checkMaximum(jPath, index, max, errors) ?: true

/**
 * Validates that the optional target value is within the inclusive valid range
 * if present.
 *
 * @receiver Optional target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid value.
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinimum
 * @see optCheckMaximum
 *
 * @since 0.2.0
 */
inline fun Short?.optCheckInRange(jPath: String, min: Short, max: Short, errors: ValidationErrors) =
  this?.let {
    it.checkMinimum(jPath, min, errors)
    it.checkMaximum(jPath, max, errors)
  } ?: true

/**
 * Validates that the optional target value is within the inclusive valid range
 * if present.
 *
 * @receiver Optional target short to validate.
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
 * @param min Minimum valid value.
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinimum
 * @see optCheckMaximum
 *
 * @since 0.2.0
 */
inline fun Short?.optCheckInRange(jPath: String, index: Int, min: Short, max: Short, errors: ValidationErrors) =
  this?.let {
    it.checkMinimum(jPath, index, min, errors)
    it.checkMaximum(jPath, index, max, errors)
  }

// endregion Optional

// region Required

/**
 * Validates that the nullable target value is present and is greater than or
 * equal to the given minimum value.
 *
 * @receiver Nullable target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see reqCheckMaximum
 * @see reqCheckInRange
 *
 * @since 0.2.0
 */
@OptIn(ExperimentalContracts::class)
inline fun Short?.reqCheckMinimum(jPath: String, min: Short, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMinimum != null) }

  return this?.checkMinimum(jPath, min, errors)
    ?: false.also { errors.add(jPath, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is greater than or
 * equal to the given minimum value.
 *
 * @receiver Nullable target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see reqCheckMaximum
 * @see reqCheckInRange
 *
 * @since 0.2.0
 */
@OptIn(ExperimentalContracts::class)
inline fun Short?.reqCheckMinimum(jPath: String, index: Int, min: Short, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMinimum != null) }

  return this?.checkMinimum(jPath, index, min, errors)
    ?: false.also { errors.add(jPath..index, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is less than or
 * equal to the given maximum value.
 *
 * @receiver Nullable target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see reqCheckMinimum
 * @see reqCheckInRange
 *
 * @since 0.2.0
 */
@OptIn(ExperimentalContracts::class)
inline fun Short?.reqCheckMaximum(jPath: String, max: Short, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMaximum != null) }

  return this?.checkMaximum(jPath, max, errors)
    ?: false.also { errors.add(jPath, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is less than or
 * equal to the given maximum value.
 *
 * @receiver Nullable target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see reqCheckMinimum
 * @see reqCheckInRange
 *
 * @since 0.2.0
 */
@OptIn(ExperimentalContracts::class)
inline fun Short?.reqCheckMaximum(jPath: String, index: Int, max: Short, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMaximum != null) }

  return this?.checkMaximum(jPath, index, max, errors)
    ?: false.also { errors.add(jPath..index, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is within the
 * inclusive valid range.
 *
 * @receiver Nullable target short to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid value.
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see reqCheckMinimum
 * @see reqCheckMaximum
 *
 * @since 0.2.0
 */
@OptIn(ExperimentalContracts::class)
inline fun Short?.reqCheckInRange(jPath: String, min: Short, max: Short, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckInRange != null) }

  return this?.let { it.checkMinimum(jPath, min, errors) && it.checkMaximum(jPath, max, errors) }
    ?: false.also { errors.add(jPath, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is within the
 * inclusive valid range.
 *
 * @receiver Nullable target short to validate.
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
 * @param min Minimum valid value.
 *
 * @param max Maximum valid value.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see reqCheckMinimum
 * @see reqCheckMaximum
 *
 * @since 0.2.0
 */
@OptIn(ExperimentalContracts::class)
inline fun Short?.reqCheckInRange(jPath: String, index: Int, min: Short, max: Short, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckInRange != null) }

  return this?.let { it.checkMinimum(jPath, index, min, errors) && it.checkMaximum(jPath, index, max, errors) }
    ?: false.also { errors.add(jPath..index, messageIndex.nullErrorMessage) }
}

// endregion Required