@file:JvmName("JPath")
@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.request.validation

/**
 * Defines an operator overload for the `..` operator to allow shorthand JSON
 * path construction.
 *
 * The given [child] string will be appended to the JSON path as an object key
 * in the format `receiver.child`.
 *
 * @receiver JSON path to the parent object to which the child field name will
 * be appended.
 *
 * @param child Name of the object key to append to the JSON path.
 *
 * @return New JSON path string.
 *
 * @since 0.1.0
 */
@JvmSynthetic
inline operator fun String.rangeTo(child: String) = "$this.$child"

/**
 * Defines an operator overload for the `..` operator to allow shorthand JSON
 * path construction.
 *
 * The given [index] value will be appended to the JSON path as an array index
 * in the format `receiver[index]`.
 *
 * @receiver JSON path to the parent object to which the child field name will
 * be appended.
 *
 * @param index Array element index to append to the JSON path.
 *
 * @return New JSON path string.
 *
 * @since 0.1.0
 */
@JvmSynthetic
inline operator fun String.rangeTo(index: Int) = "$this[$index]"

/**
 * Appends the given [child] to the target JSON path as an object key in the
 * format `parent.child`.
 *
 * @receiver JSON path to the parent object to which the child field name will
 * be appended.
 *
 * @param child Name of the object key to append to the JSON path.
 *
 * @return New JSON path string.
 *
 * @since 0.1.0
 */
inline fun String.append(child: String) = "$this.$child"

/**
 * Appends the given [index] to the JSON path as an array index in the format
 * `parent[index]`.
 *
 * @receiver JSON path to the parent object to which the child field name will
 * be appended.
 *
 * @param index Array element index to append to the JSON path.
 *
 * @return New JSON path string.
 *
 * @since 0.1.0
 */
inline fun String.append(index: Int) = "$this[$index]"
