databaseChangeLog = {

    changeSet(author: "pledbrook (generated)", id: "1352703305372-1") {
        createTable(tableName: "authority") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "authorityPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-2") {
        createTable(tableName: "person") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "personPK")
            }

            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "boolean") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "boolean") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "boolean") {
                constraints(nullable: "false")
            }

            column(name: "password_hash", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "boolean") {
                constraints(nullable: "false")
            }

            column(name: "real_name", type: "varchar(255)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-3") {
        createTable(tableName: "person_authority") {
            column(name: "authority_id", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "person_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-4") {
        createTable(tableName: "person_person") {
            column(name: "person_followed_id", type: "bigint")

            column(name: "person_id", type: "bigint")
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-5") {
        createTable(tableName: "status") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "statusPK")
            }

            column(name: "version", type: "bigint") { constraints(nullable: "false") }
            column(name: "author_id", type: "bigint") { constraints(nullable: "false") }
            column(name: "date_created", type: "timestamp") { constraints(nullable: "false") }
            column(name: "message", type: "varchar(255)") { constraints(nullable: "false") }
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-6") {
        addPrimaryKey(columnNames: "authority_id, person_id", constraintName: "person_authorPK", tableName: "person_authority")
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-7") {
        addForeignKeyConstraint(baseColumnNames: "authority_id", baseTableName: "person_authority", constraintName: "FK2C8236D95E56FA99", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authority", referencesUniqueColumn: "false")
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-8") {
        addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "person_authority", constraintName: "FK2C8236D96E65723B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-9") {
        addForeignKeyConstraint(baseColumnNames: "person_followed_id", baseTableName: "person_person", constraintName: "FKBFFF837F84C311B6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-10") {
        addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "person_person", constraintName: "FKBFFF837F6E65723B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-11") {
        addForeignKeyConstraint(baseColumnNames: "author_id", baseTableName: "status", constraintName: "FKCACDCFF2937EB845", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-12") {
        createIndex(indexName: "authority_unique_1352703305294", tableName: "authority", unique: "true") {
            column(name: "authority")
        }
    }

    changeSet(author: "pledbrook (generated)", id: "1352703305372-13") {
        createIndex(indexName: "username_unique_1352703305303", tableName: "person", unique: "true") {
            column(name: "username")
        }
    }
}
