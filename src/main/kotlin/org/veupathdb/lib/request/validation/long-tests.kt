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
 * @receiver Target int to validate.
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
inline fun Long.checkMinimum(jPath: String, min: Long, errors: ValidationErrors): Boolean {
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
 * @receiver Target int to validate.
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
inline fun Long.checkMinimum(jPath: String, index: Int, min: Long, errors: ValidationErrors): Boolean {
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
 * @receiver Target int to validate.
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
inline fun Long.checkMaximum(jPath: String, max: Long, errors: ValidationErrors): Boolean {
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
 * @receiver Target int to validate.
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
inline fun Long.checkMaximum(jPath: String, index: Int, max: Long, errors: ValidationErrors): Boolean {
  if (this > max) {
    errors.add(jPath..index, messageIndex.maxValueErrorMessage(max, this))
    return false
  }

  return true
}

/**
 * Validates that the target value is within the inclusive valid range.
 *
 * @receiver Target int to validate.
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
inline fun Long.checkInRange(jPath: String, min: Long, max: Long, errors: ValidationErrors) =
  checkMinimum(jPath, min, errors) && checkMaximum(jPath, max, errors)

/**
 * Validates that the target value is within the inclusive valid range.
 *
 * @receiver Target int to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param range Valid value range.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMinimum
 * @see checkMaximum
 *
 * @since 0.2.0
 */
inline fun Long.checkInRange(jPath: String, range: LongRange, errors: ValidationErrors) =
  checkMinimum(jPath, range.first, errors) && checkMaximum(jPath, range.last, errors)

/**
 * Validates that the target value is within the inclusive valid range.
 *
 * @receiver Target int to validate.
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
inline fun Long.checkInRange(jPath: String, index: Int, min: Long, max: Long, errors: ValidationErrors) =
  checkMinimum(jPath, index, min, errors) && checkMaximum(jPath, index, max, errors)

/**
 * Validates that the target value is within the inclusive valid range.
 *
 * @receiver Target int to validate.
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
 * @param range Valid value range.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see checkMinimum
 * @see checkMaximum
 *
 * @since 0.2.0
 */
inline fun Long.checkInRange(jPath: String, index: Int, range: LongRange, errors: ValidationErrors) =
  checkMinimum(jPath, index, range.first, errors) && checkMaximum(jPath, index, range.last, errors)

// endregion Non Null

// region Optional

/**
 * Validates that the optional target value is greater than or equal to the
 * given minimum value if present
 *
 * @receiver Optional target int to validate.
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
inline fun Long?.optCheckMinimum(jPath: String, min: Long, errors: ValidationErrors) =
  this?.checkMinimum(jPath, min, errors) ?: true

/**
 * Validates that the optional target value is greater than or equal to the
 * given minimum value if present
 *
 * @receiver Optional target int to validate.
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
inline fun Long?.optCheckMinimum(jPath: String, index: Int, min: Long, errors: ValidationErrors) =
  this?.checkMinimum(jPath, index, min, errors) ?: true

/**
 * Validates that the optional target value is less than or equal to the
 * given maximum value if present
 *
 * @receiver Optional target int to validate.
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
inline fun Long?.optCheckMaximum(jPath: String, max: Long, errors: ValidationErrors) =
  this?.checkMaximum(jPath, max, errors) ?: true

/**
 * Validates that the optional target value is less than or equal to the
 * given maximum value if present
 *
 * @receiver Optional target int to validate.
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
inline fun Long?.optCheckMaximum(jPath: String, index: Int, max: Long, errors: ValidationErrors) =
  this?.checkMaximum(jPath, index, max, errors) ?: true

/**
 * Validates that the optional target value is within the inclusive valid range
 * if present.
 *
 * @receiver Optional target int to validate.
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
inline fun Long?.optCheckInRange(jPath: String, min: Long, max: Long, errors: ValidationErrors) =
  this?.let {
    it.checkMinimum(jPath, min, errors)
    it.checkMaximum(jPath, max, errors)
  } ?: true

/**
 * Validates that the optional target value is within the inclusive valid range
 * if present.
 *
 * @receiver Optional target int to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param range Valid value range.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinimum
 * @see optCheckMaximum
 *
 * @since 0.2.0
 */
inline fun Long?.optCheckInRange(jPath: String, range: LongRange, errors: ValidationErrors) =
  this?.let {
    it.checkMinimum(jPath, range.first, errors)
    it.checkMaximum(jPath, range.last, errors)
  } ?: true

/**
 * Validates that the optional target value is within the inclusive valid range
 * if present.
 *
 * @receiver Optional target int to validate.
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
inline fun Long?.optCheckInRange(jPath: String, index: Int, min: Long, max: Long, errors: ValidationErrors) =
  this?.let {
    it.checkMinimum(jPath, index, min, errors)
    it.checkMaximum(jPath, index, max, errors)
  }

/**
 * Validates that the optional target value is within the inclusive valid range
 * if present.
 *
 * @receiver Optional target int to validate.
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
 * @param range Valid value range.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinimum
 * @see optCheckMaximum
 *
 * @since 0.2.0
 */
inline fun Long?.optCheckInRange(jPath: String, index: Int, range: LongRange, errors: ValidationErrors) =
  this?.let {
    it.checkMinimum(jPath, index, range.first, errors)
    it.checkMaximum(jPath, index, range.last, errors)
  }

// endregion Optional

// region Required

/**
 * Validates that the nullable target value is present and is greater than or
 * equal to the given minimum value.
 *
 * @receiver Nullable target int to validate.
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
inline fun Long?.reqCheckMinimum(jPath: String, min: Long, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMinimum != null) }

  return this?.checkMinimum(jPath, min, errors)
    ?: false.also { errors.add(jPath, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is greater than or
 * equal to the given minimum value.
 *
 * @receiver Nullable target int to validate.
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
inline fun Long?.reqCheckMinimum(jPath: String, index: Int, min: Long, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMinimum != null) }

  return this?.checkMinimum(jPath, index, min, errors)
    ?: false.also { errors.add(jPath..index, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is less than or
 * equal to the given maximum value.
 *
 * @receiver Nullable target int to validate.
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
inline fun Long?.reqCheckMaximum(jPath: String, max: Long, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMaximum != null) }

  return this?.checkMaximum(jPath, max, errors)
    ?: false.also { errors.add(jPath, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is less than or
 * equal to the given maximum value.
 *
 * @receiver Nullable target int to validate.
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
inline fun Long?.reqCheckMaximum(jPath: String, index: Int, max: Long, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckMaximum != null) }

  return this?.checkMaximum(jPath, index, max, errors)
    ?: false.also { errors.add(jPath..index, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is within the
 * inclusive valid range.
 *
 * @receiver Nullable target int to validate.
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
inline fun Long?.reqCheckInRange(jPath: String, min: Long, max: Long, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckInRange != null) }

  return this?.let { it.checkMinimum(jPath, min, errors) && it.checkMaximum(jPath, max, errors) }
    ?: false.also { errors.add(jPath, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is within the
 * inclusive valid range.
 *
 * @receiver Nullable target int to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param range Valid value range.
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
inline fun Long?.reqCheckInRange(jPath: String, range: LongRange, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckInRange != null) }

  return this?.let { it.checkMinimum(jPath, range.first, errors) && it.checkMaximum(jPath, range.last, errors) }
    ?: false.also { errors.add(jPath, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is within the
 * inclusive valid range.
 *
 * @receiver Nullable target int to validate.
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
inline fun Long?.reqCheckInRange(jPath: String, index: Int, min: Long, max: Long, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckInRange != null) }

  return this?.let { it.checkMinimum(jPath, index, min, errors) && it.checkMaximum(jPath, index, max, errors) }
    ?: false.also { errors.add(jPath..index, messageIndex.nullErrorMessage) }
}

/**
 * Validates that the nullable target value is present and is within the
 * inclusive valid range.
 *
 * @receiver Nullable target int to validate.
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
 * @param range Valid value range.
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
inline fun Long?.reqCheckInRange(jPath: String, index: Int, range: LongRange, errors: ValidationErrors): Boolean {
  contract { returns(true) implies(this@reqCheckInRange != null) }

  return this?.let { it.checkMinimum(jPath, index, range.first, errors) && it.checkMaximum(jPath, index, range.last, errors) }
    ?: false.also { errors.add(jPath..index, messageIndex.nullErrorMessage) }
}

// endregion Required