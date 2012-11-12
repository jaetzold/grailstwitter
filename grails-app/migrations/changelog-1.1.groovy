databaseChangeLog = {

    changeSet(author: "pledbrook (generated)", id: "1352704718387-1") {
        createTable(tableName: "tag") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tagPK")
            }

            column(name: "version", type: "bigint") { constraints(nullable: "false") }
            column(name: "name", type: "varchar(255)") { constraints(nullable: "false") }
            column(name: "status_id", type: "bigint") { constraints(nullable: "false") }
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352704718387-2") {
        addColumn(tableName: "status") {
            column(name: "ttl", type: "bigint",
                   valueNumeric: "1000000000000000000", defaultValueNumeric: "1000000000000000000") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352704718387-3") {
        addForeignKeyConstraint(baseColumnNames: "status_id", baseTableName: "tag", constraintName: "FK1BF9AB9DC8A3F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "status", referencesUniqueColumn: "false")
    }
}
