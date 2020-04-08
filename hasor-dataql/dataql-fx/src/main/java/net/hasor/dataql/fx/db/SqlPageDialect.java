/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.dataql.fx.db;
import net.hasor.dataql.fx.db.parser.FxSql;
import net.hasor.db.jdbc.PreparedStatementSetter;
import net.hasor.db.jdbc.core.ArgPreparedStatementSetter;

import java.util.Map;

/**
 * 数据库方言，针对不同数据库进行实现
 * @author 赵永春 (zyc@hasor.net)
 * @version : 2020-04-08
 */
public interface SqlPageDialect {
    /** 生成 count 查询 sql */
    public SqlPageQuery getCountSql(FxSql fxSql, Map<String, Object> paramMap);

    /** 生成分页查询 sql */
    public SqlPageQuery getPageSql(FxSql fxSql, Map<String, Object> paramMap, int start, int limit);

    public static class SqlPageQuery {
        private String   sqlString;
        private Object[] paramArray;

        public SqlPageQuery(String sqlString, Object[] paramArray) {
            this.sqlString = sqlString;
            this.paramArray = paramArray;
        }

        public String getSqlString() {
            return this.sqlString;
        }

        public PreparedStatementSetter getParamMap() {
            return new ArgPreparedStatementSetter(this.paramArray);
        }
    }
}