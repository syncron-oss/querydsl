/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.jdo.sql;

import java.util.List;

import com.mysema.query.JoinExpression;
import com.mysema.query.JoinFlag;
import com.mysema.query.QueryFlag;
import com.mysema.query.QueryFlag.Position;
import com.mysema.query.QueryMetadata;
import com.mysema.query.sql.ForeignKey;
import com.mysema.query.sql.RelationalFunctionCall;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.SQLQueryMixin;
import com.mysema.query.support.ProjectableQuery;
import com.mysema.query.support.QueryMixin;
import com.mysema.query.types.Expression;
import com.mysema.query.types.ExpressionUtils;
import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.SubQueryExpression;
import com.mysema.query.types.expr.Wildcard;
import com.mysema.query.types.template.NumberTemplate;
import com.mysema.query.types.template.SimpleTemplate;

/**
 * Base class for JDO based SQLQuery implementations
 *
 * @author tiwe
 *
 * @param <T>
 */
public abstract class AbstractSQLQuery<T extends AbstractSQLQuery<T>> extends ProjectableQuery<T> {

    protected final SQLQueryMixin<T> queryMixin;
    
    @SuppressWarnings("unchecked")
    public AbstractSQLQuery(QueryMetadata metadata) {
        super(new QueryMixin<T>(metadata));
        this.queryMixin = (SQLQueryMixin<T>)super.queryMixin;
        this.queryMixin.setSelf((T)this);
    }

    @Override
    public long count() {
        return uniqueResult(Wildcard.countAsInt);
    }

    @Override
    public boolean exists() {
        return limit(1).uniqueResult(NumberTemplate.ONE) != null;
    }

    public T from(Expression<?>... args) {
        return queryMixin.from(args);
    }

    @SuppressWarnings("unchecked")
    public T from(SubQueryExpression<?> subQuery, Path<?> alias) {
        return queryMixin.from(ExpressionUtils.as((Expression)subQuery, alias));
    }

    public T fullJoin(RelationalPath<?> o) {
        return queryMixin.fullJoin(o);
    }
    
    public <E> T fullJoin(RelationalFunctionCall<E> target, Path<E> alias) {
        return queryMixin.fullJoin(target, alias);
    }
    
    public <E> T fullJoin(ForeignKey<E> key, RelationalPath<E> entity) {
        return queryMixin.fullJoin(entity).on(key.on(entity));
    }

    public T fullJoin(SubQueryExpression<?> o, Path<?> alias) {
        return queryMixin.fullJoin(o, alias);
    }

    public QueryMetadata getMetadata() {
        return queryMixin.getMetadata();
    }

    public T innerJoin(RelationalPath<?> o) {
        return queryMixin.innerJoin(o);
    }
    
    public <E> T innerJoin(RelationalFunctionCall<E> target, Path<E> alias) {
        return queryMixin.innerJoin(target, alias);
    }
    
    public <E> T innerJoin(ForeignKey<E> key, RelationalPath<E> entity) {
        return queryMixin.innerJoin(entity).on(key.on(entity));
    }

    public T innerJoin(SubQueryExpression<?> o, Path<?> alias) {
        return queryMixin.innerJoin(o, alias);
    }
    
    public T join(RelationalPath<?> o) {
        return queryMixin.join(o);
    }
    
    public <E> T join(RelationalFunctionCall<E> target, Path<E> alias) {
        return queryMixin.join(target, alias);
    }
    
    public <E> T join(ForeignKey<E> key, RelationalPath<E> entity) {
        return queryMixin.join(entity).on(key.on(entity));
    }

    public T join(SubQueryExpression<?> o, Path<?> alias) {
        return queryMixin.join(o, alias);
    }

    public T leftJoin(RelationalPath<?> o) {
        return queryMixin.leftJoin(o);
    }
    
    public <E> T leftJoin(RelationalFunctionCall<E> target, Path<E> alias) {
        return queryMixin.leftJoin(target, alias);
    }

    public <E> T leftJoin(ForeignKey<E> key, RelationalPath<E> entity) {
        return queryMixin.leftJoin(entity).on(key.on(entity));
    }

    public T leftJoin(SubQueryExpression<?> o, Path<?> alias) {
        return queryMixin.leftJoin(o, alias);
    }

    public T on(Predicate... conditions) {
        return queryMixin.on(conditions);
    }

    public T rightJoin(RelationalPath<?> o) {
        return queryMixin.rightJoin(o);
    }
    
    public <E> T rightJoin(RelationalFunctionCall<E> target, Path<E> alias) {
        return queryMixin.rightJoin(target, alias);
    }

    public <E> T rightJoin(ForeignKey<E> key, RelationalPath<E> entity) {
        return queryMixin.rightJoin(entity).on(key.on(entity));
    }
    
    public T rightJoin(SubQueryExpression<?> o, Path<?> alias) {
        return queryMixin.rightJoin(o, alias);
    }

    public T addJoinFlag(String flag) {
        return addJoinFlag(flag, JoinFlag.Position.BEFORE_TARGET);
    }

    @SuppressWarnings("unchecked")
    public T addJoinFlag(String flag, JoinFlag.Position position) {
        List<JoinExpression> joins = queryMixin.getMetadata().getJoins();
        joins.get(joins.size()-1).addFlag(new JoinFlag(flag, position));
        return (T)this;
    }

    public T addFlag(Position position, String prefix, Expression<?> expr) {
        Expression<?> flag = SimpleTemplate.create(expr.getType(), prefix + "{0}", expr);
        return queryMixin.addFlag(new QueryFlag(position, flag));
    }

    public T addFlag(Position position, String flag) {
        return queryMixin.addFlag(new QueryFlag(position, flag));
    }

    public T addFlag(Position position, Expression<?> flag) {
        return queryMixin.addFlag(new QueryFlag(position, flag));
    }

}
