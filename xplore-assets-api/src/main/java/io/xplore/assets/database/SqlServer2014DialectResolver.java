package io.xplore.assets.database;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.engine.jdbc.dialect.internal.StandardDialectResolver;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;

/**
 * This class should resolve SQL dialect issues for Hibernate
 * Make sure to change the property "hibernate.dialect_resolvers" in file "resorces/META-INF/persistence.xml"
 *
 * See: http://stackoverflow.com/questions/33521144/wrong-hibernate-dialect-for-mssql-2014?answertab=active#tab-top
 */
public class SqlServer2014DialectResolver implements DialectResolver {

    private static final long serialVersionUID = 1L;

    @Override
    public Dialect resolveDialect(DialectResolutionInfo info) {
        Dialect customDialectResolver = customDialectResolver(info);
        return customDialectResolver;
    }

    private Dialect customDialectResolver(DialectResolutionInfo info) {
        final String databaseName = info.getDatabaseName();
        final int majorVersion = info.getDatabaseMajorVersion();
        if (isSqlServer2014(databaseName, majorVersion)) {
            return new SQLServer2012Dialect();
        } else {
            return StandardDialectResolver.INSTANCE.resolveDialect(info);
        }
    }

    private boolean isSqlServer2014(final String databaseName, final int majorVersion) {
        return databaseName.startsWith("Microsoft SQL Server") && majorVersion == 12;
    }

}
