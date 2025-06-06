@file:JvmMultifileClass
@file:JvmName("Validation")
@file:Suppress("NOTHING_TO_INLINE")
package org.veupathdb.lib.request.validation

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

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
 * @see checkMinLength
 * @see checkLength
 *
 * @since 0.1.0
 */
inline fun String.checkMaxLength(jPath: String, max: Int, errors: ValidationErrors) {
  toByteArray().size.also { if (it > max) errors.add(jPath, messageIndex.maxLengthErrorMessage(max, it)) }
}

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
 * @see checkMinLength
 * @see checkLength
 *
 * @since 0.1.0
 */
inline fun String.checkMaxLength(jPath: String, index: Int, max: Int, errors: ValidationErrors) {
  toByteArray().size.also { if (it > max) errors.add(jPath..index, messageIndex.maxLengthErrorMessage(max, it)) }
}

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
 * @see checkMaxLength
 * @see checkLength
 *
 * @since 0.1.0
 */
inline fun String.checkMinLength(jPath: String, min: Int, errors: ValidationErrors) {
  if (length < min)
    errors.add(jPath, messageIndex.minLengthErrorMessage(min, length))
}

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
 * @see checkMaxLength
 * @see checkLength
 *
 * @since 0.1.0
 */
inline fun String.checkMinLength(jPath: String, index: Int, min: Int, errors: ValidationErrors) {
  if (length < min)
    errors.add(jPath..index, messageIndex.minLengthErrorMessage(min, length))
}

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
 * @see checkMinLength
 * @see checkMaxLength
 *
 * @since 0.1.0
 */
inline fun String.checkLength(jPath: String, min: Int, max: Int, errors: ValidationErrors) {
  checkMinLength(jPath, min, errors)
  checkMaxLength(jPath, max, errors)
}

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
 * @see checkMinLength
 * @see checkMaxLength
 *
 * @since 0.1.0
 */
inline fun String.checkLength(jPath: String, index: Int, min: Int, max: Int, errors: ValidationErrors) {
  checkMinLength(jPath, index, min, errors)
  checkMaxLength(jPath, index, max, errors)
}

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
inline fun String?.optCheckMaxLength(jPath: String, max: Int, errors: ValidationErrors) {
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
inline fun String?.optCheckMaxLength(jPath: String, index: Int, max: Int, errors: ValidationErrors) {
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
inline fun String?.optCheckMinLength(jPath: String, min: Int, errors: ValidationErrors) {
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
inline fun String?.optCheckMinLength(jPath: String, index: Int, min: Int, errors: ValidationErrors) {
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
inline fun String?.optCheckLength(jPath: String, min: Int, max: Int, errors: ValidationErrors) {
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
inline fun String?.optCheckLength(jPath: String, index: Int, min: Int, max: Int, errors: ValidationErrors) {
  if (this != null) {
    checkMinLength(jPath, index, min, errors)
    checkMaxLength(jPath, index, max, errors)
  }
}

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
 * @see reqCheckMinLength
 * @see reqCheckLength
 *
 * @since 0.1.0
 */
inline fun String?.reqCheckMaxLength(jPath: String, max: Int, errors: ValidationErrors) {
  if (checkNonBlank(jPath, errors))
    checkMaxLength(jPath, max, errors)
}

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
 * @see optCheckMinLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
inline fun String?.reqCheckMaxLength(jPath: String, index: Int, max: Int, errors: ValidationErrors) {
  if (checkNonBlank(jPath, index, errors))
    checkMaxLength(jPath, index, max, errors)
}

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
 * @see optCheckMaxLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
inline fun String?.reqCheckMinLength(jPath: String, min: Int, errors: ValidationErrors) {
  if (checkNonBlank(jPath, errors))
    checkMinLength(jPath, min, errors)
}

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
 * @param errors Validation error collection into which any validation errors
 * will be added.
 *
 * @see optCheckMaxLength
 * @see optCheckLength
 *
 * @since 0.1.0
 */
inline fun String?.reqCheckMinLength(jPath: String, index: Int, min: Int, errors: ValidationErrors) {
  if (checkNonBlank(jPath, index, errors))
    checkMinLength(jPath, index, min, errors)
}

/**
 * Requires that the target string is not `null` or [blank][String.isBlank],
 * then validates that the length of the string in characters is not less than
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
 * @see reqCheckMinLength
 * @see reqCheckMaxLength
 *
 * @since 0.1.0
 */
inline fun String?.reqCheckLength(jPath: String, min: Int, max: Int, errors: ValidationErrors) {
  if (checkNonBlank(jPath, errors))
    checkLength(jPath, min, max, errors)
}

/**
 * Requires that the target string is not `null` or [blank][String.isBlank],
 * then validates that the length of the string in characters is not less than
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
 * @see reqCheckMinLength
 * @see reqCheckMaxLength
 *
 * @since 0.1.0
 */
inline fun String?.reqCheckLength(jPath: String, index: Int, min: Int, max: Int, errors: ValidationErrors) {
  if (checkNonBlank(jPath, index, errors))
    checkLength(jPath, index, min, max, errors)
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
 * @since 0.1.0
 */
@OptIn(ExperimentalContracts::class)
inline fun String?.checkNonBlank(jPath: String, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNonBlank != null) }

  if (this == null)
    errors.add(jPath, messageIndex.nullErrorMessage)
  else if (isBlank())
    errors.add(jPath, messageIndex.blankErrorMessage)
  else
    return true

  return false
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
inline fun String?.checkNonBlank(jPath: String, index: Int, errors: ValidationErrors): Boolean {
  contract { returns(true) implies (this@checkNonBlank != null) }

  if (this == null)
    errors.add(jPath..index, messageIndex.nullErrorMessage)
  else if (isBlank())
    errors.add(jPath..index, messageIndex.blankErrorMessage)
  else
    return true

  return false
}
