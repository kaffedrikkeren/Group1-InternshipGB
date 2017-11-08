package libdb.modelPostgreSQL.keyword;

import libdb.SqlSpecification;
import libdb.entities.Keyword;
import libdb.entities.Person;
import libdb.modelPostgreSQL.person.PersonRepository;

public class KeywordSpecifications implements SqlSpecification {
    /**
     * UserId specification
     *
     */
    public static class ByUserId implements SqlSpecification {
        private Integer userId;

        public ByUserId(final Integer userId) {
            this.userId = userId;
        }

        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d ORDER BY \"%4$s\";",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("userId"),
                    userId,
                    KeywordRepository.getNameFieldDB("name")
            );
        }
    }

    /**
     * PersonID and UserId specification
     *
     */
    public static class ByPersonIdAndUserId implements SqlSpecification {
        private Integer personId;
        private Integer userId;

        public ByPersonIdAndUserId(final Integer personId, final Integer userId) {
            this.personId = personId;
            this.userId = userId;
        }

        public String toSqlQuery() {
            return String.format(
                    "SELECT * FROM %1$s WHERE \"%2$s\" = %3$d AND \"%4$s\" = %5$d ORDER BY \"%4$s\";",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("personId"),
                    personId,
                    KeywordRepository.getNameFieldDB("userId"),
                    userId,
                    KeywordRepository.getNameFieldDB("name")
            );
        }
    }

    /**
     * DELETE specification
     *
     */
    public static class DeleteKeyword implements SqlSpecification {
        private Keyword keyword;

        public DeleteKeyword(final Keyword keyword) {
            this.keyword = keyword;
        }

        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                    PersonRepository.getTableName(),
                    PersonRepository.getNameFieldDB("id"),
                    keyword.getId()
            );
        }
    }

    /**
     * DELETE PersonId specification
     *
     */
    public static class DeleteKeywordsByPersonId implements SqlSpecification {
        private Person person;

        public DeleteKeywordsByPersonId(final Person person) {
            this.person = person;
        }

        public String toSqlQuery() {
            return String.format(
                    "DELETE FROM %1$s WHERE \"%2$s\" = %3$d;",
                    KeywordRepository.getTableName(),
                    KeywordRepository.getNameFieldDB("personId"),
                    person.getId()
            );
        }
    }

    public String toSqlQuery() {
        return null;
    }
}