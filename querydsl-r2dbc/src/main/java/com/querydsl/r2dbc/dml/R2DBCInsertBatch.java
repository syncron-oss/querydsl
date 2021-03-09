/*
 * Copyright 2015, The Querydsl Team (http://www.querydsl.com/team)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.querydsl.r2dbc.dml;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.SubQueryExpression;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code SQLInsertBatch} defines the state of an SQL INSERT batch item
 *
 * @author mc_fish
 */
public class R2DBCInsertBatch {

    private final List<Path<?>> columns;

    private final List<Expression<?>> values;

    @Nullable
    private final SubQueryExpression<?> subQuery;

    public R2DBCInsertBatch(List<Path<?>> c, List<Expression<?>> v, @Nullable SubQueryExpression<?> sq) {
        columns = new ArrayList<Path<?>>(c);
        values = new ArrayList<Expression<?>>(v);
        subQuery = sq;
    }

    public List<Path<?>> getColumns() {
        return columns;
    }

    public List<Expression<?>> getValues() {
        return values;
    }

    public SubQueryExpression<?> getSubQuery() {
        return subQuery;
    }


}