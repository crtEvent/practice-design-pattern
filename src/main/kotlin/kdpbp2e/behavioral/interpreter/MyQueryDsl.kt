package kdpbp2e.behavioral.interpreter

fun main() {

    val sql1 = select("name, age") {
        from("users") {
            where("age > 25")
        } // Closes from
    } // Closes select

    println(sql1) // "SELECT name, age FROM users WHERE age > 25"

    val sql2 = select("name, age") {
        from("users") {
            where("age > 25")
            orderBy("age DESC")
        } // Closes from
    } // Closes select

    println(sql2) // "SELECT name, age FROM users WHERE age > 25 ORDER BY age DESC"
}

fun select(columns: String, from: SelectClause.() -> Unit): SelectClause {
    return SelectClause(columns).apply(from)
}

class SelectClause(private val columns: String) {
    private lateinit var from: FromClause

    fun from(
        table: String,
        where: FromClause.() -> Unit
    ): FromClause
    {
        this.from = FromClause(table)
        return this.from.apply(where)
    }

    override fun toString() = "SELECT $columns $from"
}

class FromClause(private val table: String) {
    private lateinit var where: WhereClause
    private var orderBy: OrderByClause? = null

    fun where(conditions: String) = this.apply {
        where = WhereClause(conditions)
    }

    fun orderBy(order: String) = this.apply {
        orderBy = OrderByClause(order)
    }

    override fun toString(): String {
        val orderByClause = orderBy ?: ""
        return  "FROM $table $where $orderByClause"
    }
}

class WhereClause(private val conditions: String) {
    override fun toString() = "WHERE $conditions"
}

class OrderByClause(private val order: String) {
    override fun toString() = "ORDER BY $order"
}