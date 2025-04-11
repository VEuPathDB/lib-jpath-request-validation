package org.veupathdb.lib.request.validation

/**
 * Default implementation of the [ErrorMessageIndex] interface, providing
 * overrideable defaults for the error message strings.
 *
 * @since 0.1.0
 */
class SimpleErrorMessageIndex(
  override val nullErrorMessage: String = "must not be null",
  override val blankErrorMessage: String = "must not be blank",
  override val emptyErrorMessage: String = "must not be empty",

  /**
   * String format pattern used in calls to [minLengthErrorMessage].
   *
   * The format parameters will be provided in the following order:
   *
   * 1. minimum length
   * 2. actual length
   */
  private val minLengthErrorText: String = "must be at least %1\$d characters in length",

  /**
   * String format pattern used in calls to [maxLengthErrorMessage].
   *
   * The format parameters will be provided in the following order:
   *
   * 1. maximum length
   * 2. actual length
   */
  private val maxLengthErrorText: String = "exceeds the max allowed length of %1\$d bytes",
): ErrorMessageIndex {
  override fun minLengthErrorMessage(min: Int, actual: Int) =
    minLengthErrorText.format(min, actual)

  override fun maxLengthErrorMessage(max: Int, actual: Int) =
    maxLengthErrorText.format(max, actual)
}
