package org.veupathdb.lib.request.validation

/**
 * Central index of error messages used by the validation methods in this
 * library.
 *
 * @since 0.1.0
 */
interface ErrorMessageIndex {
  /**
   * Error message reported when a field fails validation due to being `null`.
   */
  val nullErrorMessage: String

  /**
   * Error message reported when a field fails validation due to being
   * [blank][String.isBlank].
   */
  val blankErrorMessage: String

  /**
   * Error message reported when a field fails validation due to being empty.
   *
   * For String values, consider using [blankErrorMessage] instead.
   */
  val emptyErrorMessage: String

  /**
   * Generates an error message to be reported when a text field fails
   * validation due to its character length being less than the allowed minimum
   * value.
   *
   * @param min The minimum length value that the field was tested against.
   *
   * @param actual The actual length of the text.
   *
   * @return An error message formatted with the given parameters.
   */
  fun minLengthErrorMessage(min: Int, actual: Int): String

  /**
   * Generates an error message to be reported when a text field fails
   * validation due to its size in bytes being greater than the allowed maximum
   * value.
   *
   * @param max The maximum size in bytes that the field was tested against.
   *
   * @param actual The actual size of the text in bytes.
   *
   * @return An error message formatted with the given parameters.
   */
  fun maxLengthErrorMessage(max: Int, actual: Int): String
}
