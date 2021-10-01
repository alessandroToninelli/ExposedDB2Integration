package org.jetbrains.exposed.sql.`java-time`

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Function
import org.jetbrains.exposed.sql.vendors.MysqlDialect
import org.jetbrains.exposed.sql.vendors.currentDialect
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.Temporal

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("Date", "org.jetbrains.exposed.sql.javatime")
)
class Date<T : Temporal?>(val expr: Expression<T>) : Function<LocalDate>(JavaLocalDateColumnType.INSTANCE) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder { append("DATE(", expr, ")") }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("Time", "org.jetbrains.exposed.sql.javatime")
)
class Time<T : Temporal?>(val expr: Expression<T>) : Function<LocalTime>(JavaLocalTimeColumnType.INSTANCE) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder { append("Time(", expr, ")") }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("CurrentDateTime", "org.jetbrains.exposed.sql.javatime")
)
class CurrentDateTime : Function<LocalDateTime>(JavaLocalDateTimeColumnType.INSTANCE) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        +when {
            (currentDialect as? MysqlDialect)?.isFractionDateTimeSupported() == true -> "CURRENT_TIMESTAMP(6)"
            else -> "CURRENT_TIMESTAMP"
        }
    }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("CurrentTimestamp", "org.jetbrains.exposed.sql.javatime")
)
class CurrentTimestamp<T : Temporal> : Expression<T>() {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        +when {
            (currentDialect as? MysqlDialect)?.isFractionDateTimeSupported() == true -> "CURRENT_TIMESTAMP(6)"
            else -> "CURRENT_TIMESTAMP"
        }
    }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("Year", "org.jetbrains.exposed.sql.javatime")
)
class Year<T : Temporal?>(val expr: Expression<T>) : Function<Int>(IntegerColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        currentDialect.functionProvider.year(expr, queryBuilder)
    }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("Month", "org.jetbrains.exposed.sql.javatime")
)
class Month<T : Temporal?>(val expr: Expression<T>) : Function<Int>(IntegerColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        currentDialect.functionProvider.month(expr, queryBuilder)
    }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("Day", "org.jetbrains.exposed.sql.javatime")
)
class Day<T : Temporal?>(val expr: Expression<T>) : Function<Int>(IntegerColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        currentDialect.functionProvider.day(expr, queryBuilder)
    }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("Hour", "org.jetbrains.exposed.sql.javatime")
)
class Hour<T : Temporal?>(val expr: Expression<T>) : Function<Int>(IntegerColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        currentDialect.functionProvider.hour(expr, queryBuilder)
    }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("Minute", "org.jetbrains.exposed.sql.javatime")
)
class Minute<T : Temporal?>(val expr: Expression<T>) : Function<Int>(IntegerColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        currentDialect.functionProvider.minute(expr, queryBuilder)
    }
}

@Deprecated(
    message = "Use same class from javatime package",
    replaceWith = ReplaceWith("JavSecondaLocalDateColumnType", "org.jetbrains.exposed.sql.javatime")
)
class Second<T : Temporal?>(val expr: Expression<T>) : Function<Int>(IntegerColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        currentDialect.functionProvider.second(expr, queryBuilder)
    }
}

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("date", "org.jetbrains.exposed.sql.javatime")
)
fun <T : Temporal?> Expression<T>.date(): Date<T> = Date(this)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("year", "org.jetbrains.exposed.sql.javatime")
)
fun <T : Temporal?> Expression<T>.year(): Year<T> = Year(this)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("month", "org.jetbrains.exposed.sql.javatime")
)
fun <T : Temporal?> Expression<T>.month(): Month<T> = Month(this)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("day", "org.jetbrains.exposed.sql.javatime")
)
fun <T : Temporal?> Expression<T>.day(): Day<T> = Day(this)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("hour", "org.jetbrains.exposed.sql.javatime")
)
fun <T : Temporal?> Expression<T>.hour(): Hour<T> = Hour(this)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("minute", "org.jetbrains.exposed.sql.javatime")
)
fun <T : Temporal?> Expression<T>.minute(): Minute<T> = Minute(this)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("second", "org.jetbrains.exposed.sql.javatime")
)
fun <T : Temporal?> Expression<T>.second(): Second<T> = Second(this)

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("dateParam", "org.jetbrains.exposed.sql.javatime")
)
fun dateParam(value: LocalDate): Expression<LocalDate> = QueryParameter(value, JavaLocalDateColumnType.INSTANCE)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("timeParam", "org.jetbrains.exposed.sql.javatime")
)
fun timeParam(value: LocalTime): Expression<LocalTime> = QueryParameter(value, JavaLocalTimeColumnType.INSTANCE)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("dateTimeParam", "org.jetbrains.exposed.sql.javatime")
)
fun dateTimeParam(value: LocalDateTime): Expression<LocalDateTime> =
    QueryParameter(value, JavaLocalDateTimeColumnType.INSTANCE)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("timestampParam", "org.jetbrains.exposed.sql.javatime")
)
fun timestampParam(value: Instant): Expression<Instant> = QueryParameter(value, JavaInstantColumnType.INSTANCE)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("durationParam", "org.jetbrains.exposed.sql.javatime")
)
fun durationParam(value: Duration): Expression<Duration> = QueryParameter(value, JavaDurationColumnType.INSTANCE)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("dateLiteral", "org.jetbrains.exposed.sql.javatime")
)
fun dateLiteral(value: LocalDate): LiteralOp<LocalDate> = LiteralOp(JavaLocalDateColumnType.INSTANCE, value)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("timeLiteral", "org.jetbrains.exposed.sql.javatime")
)
fun timeLiteral(value: LocalTime): LiteralOp<LocalTime> = LiteralOp(JavaLocalTimeColumnType.INSTANCE, value)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("dateTimeLiteral", "org.jetbrains.exposed.sql.javatime")
)
fun dateTimeLiteral(value: LocalDateTime): LiteralOp<LocalDateTime> = LiteralOp(JavaLocalDateTimeColumnType.INSTANCE, value)

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("timestampLiteral", "org.jetbrains.exposed.sql.javatime")
)
fun timestampLiteral(value: Instant): LiteralOp<Instant> = LiteralOp(JavaInstantColumnType.INSTANCE, value)
@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("durationLiteral", "org.jetbrains.exposed.sql.javatime")
)
fun durationLiteral(value: Duration): LiteralOp<Duration> = LiteralOp(JavaDurationColumnType.INSTANCE, value)

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("CustomDateFunction", "org.jetbrains.exposed.sql.javatime")
)
@Suppress("FunctionName")
fun CustomDateFunction(functionName: String, vararg params: Expression<*>): CustomFunction<LocalDate?> =
    CustomFunction(functionName, JavaLocalDateColumnType.INSTANCE, *params)

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("CustomTimeFunction", "org.jetbrains.exposed.sql.javatime")
)
@Suppress("FunctionName")
fun CustomTimeFunction(functionName: String, vararg params: Expression<*>): CustomFunction<LocalTime?> =
    CustomFunction(functionName, JavaLocalTimeColumnType.INSTANCE, *params)

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("CustomDateTimeFunction", "org.jetbrains.exposed.sql.javatime")
)
@Suppress("FunctionName")
fun CustomDateTimeFunction(functionName: String, vararg params: Expression<*>): CustomFunction<LocalDateTime?> =
    CustomFunction(functionName, JavaLocalDateTimeColumnType.INSTANCE, *params)

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("CustomTimeStampFunction", "org.jetbrains.exposed.sql.javatime")
)
@Suppress("FunctionName")
fun CustomTimeStampFunction(functionName: String, vararg params: Expression<*>): CustomFunction<Instant?> =
    CustomFunction(functionName, JavaInstantColumnType.INSTANCE, *params)

@Deprecated(
    message = "Use same function from javatime package",
    replaceWith = ReplaceWith("CustomDurationFunction", "org.jetbrains.exposed.sql.javatime")
)
@Suppress("FunctionName")
fun CustomDurationFunction(functionName: String, vararg params: Expression<*>): CustomFunction<Duration?> =
    CustomFunction(functionName, JavaDurationColumnType.INSTANCE, *params)
