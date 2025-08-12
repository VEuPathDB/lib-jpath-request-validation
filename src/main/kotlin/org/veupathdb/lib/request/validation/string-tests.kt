@file:JvmMultifileClass
@file:JvmName("Validation")
@file:Suppress("KDocUnresolvedReference")

package org.veupathdb.lib.request.validation

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

// region Already Not Null

/**
 * Validates that the length of the target string in bytes does not exceed the
 * given maximum.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length is less than or equal to the given
 * [max] value, `false` if the target string is longer than the given max length
 * value.
 *
 * @see checkMinLength
 * @see checkLength
 *
 * @since 0.1.0
 */
fun String.checkMaxLength(jPath: String, max: Int, errors: ValidationErrors) =
  toByteArray()
    .size
    .let { if (it > max) {
      errors.add(jPath, messageIndex.maxLengthErrorMessage(max, it))
      false
    } else
      true }

/**
 * Validates that the length of the target string in bytes does not exceed the
 * given maximum.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
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
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length is less than or equal to the given
 * [max] value, `false` if the target string is longer than the given max length
 * value.
 *
 * @see checkMinLength
 * @see checkLength
 *
 * @since 0.1.0
 */
fun String.checkMaxLength(jPath: String, index: Int, max: Int, errors: ValidationErrors) =
  toByteArray()
    .size
    .let { if (it > max) {
      errors.add(jPath..index, messageIndex.maxLengthErrorMessage(max, it))
      false
    } else
      true }

/**
 * Validates that the length of the target string in characters is not less than
 * the given minimum.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length is greater than or equal to the
 * given [min] value, `false` if the target string is shorter than the given min
 * length value.
 *
 * @see checkMaxLength
 * @see checkLength
 *
 * @since 0.1.0
 */
fun String.checkMinLength(jPath: String, min: Int, errors: ValidationErrors) =
  if (length < min) {
    errors.add(jPath, messageIndex.minLengthErrorMessage(min, length))
    false
  } else
    true

/**
 * Validates that the length of the target string in characters is not less than
 * the given minimum.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Target string to validate.
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
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length is greater than or equal to the
 * given [min] value, `false` if the target string is shorter than the given min
 * length value.
 *
 * @see checkMaxLength
 * @see checkLength
 *
 * @since 0.1.0
 */
fun String.checkMinLength(jPath: String, index: Int, min: Int, errors: ValidationErrors) =
  if (length < min) {
    errors.add(jPath..index, messageIndex.minLengthErrorMessage(min, length))
    false
  } else
    true

/**
 * Validates that the length of the target string in characters is not less than
 * the given minimum, and the length in bytes does not exceed the given maximum.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length within the given range
 * (inclusive), `false` if the target string is too short or too long.
 *
 * @see checkMinLength
 * @see checkMaxLength
 *
 * @since 0.1.0
 */
fun String.checkLength(jPath: String, min: Int, max: Int, errors: ValidationErrors) =
  checkMinLength(jPath, min, errors) && checkMaxLength(jPath, max, errors)

/**
 * Validates that the length of the target string in characters is not less than
 * the given minimum, and the length in bytes does not exceed the given maximum.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param range Inclusive range of minimum to maximum valid lengths.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length within the given range
 * (inclusive), `false` if the target string is too short or too long.
 *
 * @see checkMinLength
 * @see checkMaxLength
 *
 * @since 0.4.0
 */
fun String.checkLength(jPath: String, range: IntRange, errors: ValidationErrors) =
  checkMinLength(jPath, range.first, errors) && checkMaxLength(jPath, range.last, errors)

/**
 * Validates that the length of the target string in characters is not less than
 * the given minimum, and the length in bytes does not exceed the given maximum.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
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
 * @param range Inclusive range of minimum to maximum valid lengths.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length falls within the given inclusive
 * range.
 *
 * @see checkMinLength
 * @see checkMaxLength
 *
 * @since 0.4.0
 */
fun String.checkLength(jPath: String, index: Int, range: IntRange, errors: ValidationErrors) =
  checkMinLength(jPath, index, range.first, errors) && checkMaxLength(jPath, index, range.last, errors)

/**
 * Validates that the length of the target string in characters is not less than
 * the given minimum, and the length in bytes does not exceed the given maximum.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
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
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string length falls within the given inclusive
 * range.
 *
 * @see checkMinLength
 * @see checkMaxLength
 *
 * @since 0.1.0
 */
fun String.checkLength(jPath: String, index: Int, min: Int, max: Int, errors: ValidationErrors) =
  checkMinLength(jPath, index, min, errors) && checkMaxLength(jPath, index, max, errors)

// endregion Already Not Null

// region Deprecated Opt Tests

/**
 * Optionally validates that the length of the target string in bytes does not
 * exceed the given maximum if the string is not `null`.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Optional target string to validate when not `null`.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
@Deprecated("Redundant due to String.checkMaxLength. Will be removed in v1.0.0")
fun String?.optCheckMaxLength(jPath: String, max: Int, errors: ValidationErrors) {
  this?.toByteArray()?.size?.also { if (it > max) errors.add(jPath, messageIndex.maxLengthErrorMessage(max, it)) }
}

/**
 * Optionally validates that the length of the target string in bytes does not
 * exceed the given maximum if the string is not `null`.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Optional target string to validate when not `null`.
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
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
@Deprecated("Redundant due to String.checkMaxLength. Will be removed in v1.0.0")
fun String?.optCheckMaxLength(jPath: String, index: Int, max: Int, errors: ValidationErrors) {
  this?.toByteArray()?.size?.also { if (it > max) errors.add(jPath..index, messageIndex.maxLengthErrorMessage(max, it)) }
}

/**
 * Optionally validates that the length of the target string in characters is
 * not less than the given minimum if the target string is not `null`.
 *
 * @receiver Optional target string to validate when not `null`.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMaxLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
@Deprecated("Redundant due to String.checkMaxLength. Will be removed in v1.0.0")
fun String?.optCheckMinLength(jPath: String, min: Int, errors: ValidationErrors) {
  if (this != null && length < min)
    errors.add(jPath, messageIndex.minLengthErrorMessage(min, length))
}

/**
 * Optionally validates that the length of the target string in characters is
 * not less than the given minimum if the target string is not `null`.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Optional target string to validate when not `null`.
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
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMaxLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
@Deprecated("Redundant due to String.checkMaxLength. Will be removed in v1.0.0")
fun String?.optCheckMinLength(jPath: String, index: Int, min: Int, errors: ValidationErrors) {
  if (this != null && length < min)
    errors.add(jPath..index, messageIndex.minLengthErrorMessage(min, length))
}

/**
 * Optionally validates that the length of the target string in characters is
 * not less than the given minimum, and the length in bytes does not exceed the
 * given maximum, when the target string is not `null`.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Optional target string to validate when not `null`.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinLength
 * @see optCheckMaxLength
 *
 * @since 0.1.0
 */
@Deprecated("Redundant due to String.checkMaxLength. Will be removed in v1.0.0")
fun String?.optCheckLength(jPath: String, min: Int, max: Int, errors: ValidationErrors) {
  if (this != null) {
    checkMinLength(jPath, min, errors)
    checkMaxLength(jPath, max, errors)
  }
}

/**
 * Optionally validates that the length of the target string in characters is
 * not less than the given minimum, and the length in bytes does not exceed the
 * given maximum, when the target string is not `null`.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Optional target string to validate when not `null`.
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
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMinLength
 * @see optCheckMaxLength
 *
 * @since 0.1.0
 */
@Deprecated("Redundant due to String.checkMaxLength. Will be removed in v1.0.0")
fun String?.optCheckLength(jPath: String, index: Int, min: Int, max: Int, errors: ValidationErrors) {
  if (this != null) {
    checkMinLength(jPath, index, min, errors)
    checkMaxLength(jPath, index, max, errors)
  }
}

// endregion Deprecated Opt Tests

// region Enforce Not Null

/**
 * Requires that the target string is not `null` or [blank][String.isBlank],
 * then validates that the length of the string in bytes does not exceed the
 * given maximum.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is less
 * than or equal to the given [max] value.
 *
 * @see reqCheckMinLength
 * @see reqCheckLength
 *
 * @since 0.1.0
 */
fun String?.reqCheckMaxLength(jPath: String, max: Int, errors: ValidationErrors) =
  checkNotNull(jPath, errors) && checkMaxLength(jPath, max, errors)

/**
 * Requires that the target string is not `null` or [blank][String.isBlank],
 * then validates that the length of the string in bytes does not exceed the
 * given maximum.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
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
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is less
 * than or equal to the given [max] value.
 *
 * @see optCheckMinLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
fun String?.reqCheckMaxLength(jPath: String, index: Int, max: Int, errors: ValidationErrors) =
  checkNotBlank(jPath, index, errors) && checkMaxLength(jPath, index, max, errors)

/**
 * Requires that the target string is not `null` or [blank][String.isBlank],
 * then validates that the length of the string in characters is not less than
 * the given minimum.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is greater
 * than or equal to the given [min] value.
 *
 * @see optCheckMaxLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
fun String?.reqCheckMinLength(jPath: String, min: Int, errors: ValidationErrors) =
  checkNotBlank(jPath, errors) && checkMinLength(jPath, min, errors)

/**
 * Requires that the target string is not `null` or [blank][String.isBlank],
 * then validates that the length of the string in characters is not less than
 * the given minimum.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Target string to validate.
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
 * @param min Minimum valid length, in characters, of the target string.
 *
 * This value must be greater than or equal to `1`.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is greater
 * than or equal to the given [min] value.
 *
 * @see optCheckMaxLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
fun String?.reqCheckMinLength(jPath: String, index: Int, min: Int, errors: ValidationErrors) =
  checkNotBlank(jPath, index, errors) && checkMinLength(jPath, index, min, errors)

/**
 * Requires that the target string is not `null`, then validates that the length
 * of the string falls within the given range (inclusive).
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * This value must be greater than or equal to `1`.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is within
 * the given inclusive range.
 *
 * @see reqCheckMinLength
 * @see reqCheckMaxLength
 *
 * @since 0.1.0
 */
fun String?.reqCheckLength(jPath: String, min: Int, max: Int, errors: ValidationErrors) =
  checkNotNull(jPath, errors) && checkLength(jPath, min, max, errors)

/**
 * Requires that the target string is not `null`, then validates that the length
 * of the string falls within the given range (inclusive).
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param range Inclusive range of minimum to maximum valid lengths.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is within
 * the given inclusive range.
 *
 * @see reqCheckMinLength
 * @see reqCheckMaxLength
 *
 * @since 0.5.0
 */
fun String?.reqCheckLength(jPath: String, range: IntRange, errors: ValidationErrors) =
  checkNotNull(jPath, errors) && checkLength(jPath, range.first, range.last, errors)

/**
 * Requires that the target string is not `null`, then validates that the length
 * of the string falls within the given range (inclusive).
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
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
 * @param max Maximum valid length, in bytes, of the target string.
 *
 * @param min Minimum valid length, in characters, of the target string.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is within
 * the given inclusive range.
 *
 * @see reqCheckMinLength
 * @see reqCheckMaxLength
 *
 * @since 0.1.0
 */
fun String?.reqCheckLength(jPath: String, index: Int, min: Int, max: Int, errors: ValidationErrors) =
  checkNotBlank(jPath, index, errors) && checkLength(jPath, index, min, max, errors)

/**
 * Requires that the target string is not `null`, then validates that the length
 * of the string falls within the given range (inclusive).
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * Measuring the max length in bytes is done to avoid errors from databases
 * which define maximum column sizes in bytes rather than characters.
 *
 * @receiver Target string to validate.
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
 * @param range Inclusive range of minimum to maximum valid lengths.
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @return `true` if the target string is not `null`, and its length is within
 * the given inclusive range.
 *
 * @see reqCheckMinLength
 * @see reqCheckMaxLength
 *
 * @since 0.5.0
 */
fun String?.reqCheckLength(jPath: String, index: Int, range: IntRange, errors: ValidationErrors) =
  checkNotNull(jPath, index, errors) && checkLength(jPath, index, range.first, range.last, errors)

/**
 * Requires that the target string is not `null` or [blank][String.isBlank]
 *
 * @receiver Target string to test.
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
@Deprecated("Replace with String.checkNotBlank", replaceWith = ReplaceWith("checkNotBlank(this, jPath, errors)"))
fun String?.checkNonBlank(jPath: String, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNonBlank != null) }
  return checkNotBlank(jPath, errors)
}

/**
 * Requires that the target string is not `null` or [blank][String.isBlank]
 *
 * @receiver Target string to test.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @since 0.6.0
 */
@OptIn(ExperimentalContracts::class)
fun String?.checkNotBlank(jPath: String, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNotBlank != null) }

  return when {
    !checkNotNull(jPath, errors) -> false
    isBlank() -> {
      errors.add(jPath, messageIndex.blankErrorMessage)
      false
    }
    else -> true
  }
}

/**
 * Requires that the target string is not `null` or [blank][String.isBlank]
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Target string to test.
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
@Deprecated("Replace with String.checkNotBlank", replaceWith = ReplaceWith("checkNotBlank(this, jPath, index, errors)"))
fun String?.checkNonBlank(jPath: String, index: Int, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNonBlank != null) }
  return checkNotBlank(jPath, index, errors)
}

/**
 * Requires that the target string is not `null` or [blank][String.isBlank]
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Target string to test.
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
 * @since 0.6.0
 */
@OptIn(ExperimentalContracts::class)
fun String?.checkNotBlank(jPath: String, index: Int, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNotBlank != null) }

  return when {
    !checkNotNull(jPath, index, errors) -> false

    isBlank() -> {
      errors.add(jPath..index, messageIndex.blankErrorMessage)
      false
    }

    else -> true
  }
}

/**
 * Requires that the target string is not `null`.
 *
 * @receiver Target string to test.
 *
 * @param jPath JSON path to the element being checked.
 *
 * Example: `"meta.publication[i].citation"`
 *
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @since 0.6.0
 */
@OptIn(ExperimentalContracts::class)
fun String?.checkNotNull(jPath: String, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNotNull != null) }

  if (this == null) {
    errors.add(jPath, messageIndex.nullErrorMessage)
    return false
  }

  return true
}

/**
 * Requires that the target string is not `null`.
 *
 * This method takes the additional [index] parameter to avoid unnecessary
 * string concatenations in loops.
 *
 * @receiver Target string to test.
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
 * @since 0.6.0
 */
@OptIn(ExperimentalContracts::class)
fun String?.checkNotNull(jPath: String, index: Int, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNotNull != null) }

  if (this == null) {
    errors.add(jPath..index, messageIndex.nullErrorMessage)
    return false
  }

  return true
}

// endregion Enforce Not Null