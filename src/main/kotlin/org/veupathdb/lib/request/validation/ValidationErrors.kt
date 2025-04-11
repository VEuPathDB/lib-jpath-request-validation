package org.veupathdb.lib.request.validation

/**
 * Request Validation Error Bundle
 *
 * Container type for collecting errors while validating an incoming request.
 *
 * Errors are collected into two categories, general errors, and errors by key.
 * General errors are collected as a list, while by key errors are collected as
 * a map of lists by a given key.
 *
 * @since 0.1.0
 */
class ValidationErrors {
  val byKey   = HashMap<String, MutableList<String>>()
  val general = ArrayList<String>()

  /**
   * `true` if this [ValidationErrors] instance contains no errors.
   */
  val isEmpty
    get() = byKey.isEmpty() && general.isEmpty()

  /**
   * `true` if this [ValidationErrors] instance contains one or more errors.
   */
  val isNotEmpty
    get() = byKey.isNotEmpty() || general.isNotEmpty()

  /**
   * Adds a keyed error to this error bundle.
   */
  fun add(key: String, error: String) {
    byKey.computeIfAbsent(key) { ArrayList() }
      .add(error)
  }

  /**
   * Adds a general error to this error bundle.
   */
  fun add(error: String) {
    general.add(error)
  }
}
