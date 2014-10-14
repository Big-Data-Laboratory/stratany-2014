/*
 * Copyright 2014 Silicon Valley Data Science.
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
package com.svds.resttest.model;

import org.springframework.util.MultiValueMap;

/**
 *
 *
 */
public class GenericCountOutput {

    private String name;
    private long processTimeMillis;
    private String status;
    private String message;
    private MultiValueMap<String, String> requestParams;
    private long count;
    private String sql;
    private String databaseEngine;
    private String tableName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProcessTimeMillis() {
        return processTimeMillis;
    }

    public void setProcessTimeMillis(long processTimeMillis) {
        this.processTimeMillis = processTimeMillis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MultiValueMap<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(MultiValueMap<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDatabaseEngine() {
        return databaseEngine;
    }

    public void setDatabaseEngine(String databaseEngine) {
        this.databaseEngine = databaseEngine;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "GenericOutput{" + "name=" + name + ", processTimeMillis=" + processTimeMillis + ", status=" + status + ", message=" + message + ", requestParams=" + requestParams + ", count=" + count + ", sql=" + sql + ", databaseEngine=" + databaseEngine + ", tableName=" + tableName + '}';
    }

}
