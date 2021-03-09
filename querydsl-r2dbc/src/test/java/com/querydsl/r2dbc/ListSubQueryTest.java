package com.querydsl.r2dbc;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.r2dbc.domain.QEmployee;
import com.querydsl.r2dbc.domain.QSurvey;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ListSubQueryTest {

    @Test
    public void hashCode1() {
        QSurvey survey = QSurvey.survey;
        QSurvey survey2 = new QSurvey("survey2");
        SubQueryExpression<Tuple> query1 = R2DBCExpressions.select(survey.all()).from(survey);
        SubQueryExpression<Tuple> query2 = R2DBCExpressions.select(survey2.all()).from(survey2);

        Set<SubQueryExpression<Tuple>> queries = new HashSet<>();
        queries.add(query1);
        queries.add(query2);
        assertEquals(2, queries.size());
    }

    @Test
    public void hashCode2() {
        QSurvey survey = new QSurvey("entity");
        QEmployee employee = new QEmployee("entity");
        SubQueryExpression<Integer> query1 = R2DBCExpressions.select(survey.id).from(survey);
        SubQueryExpression<Integer> query2 = R2DBCExpressions.select(employee.id).from(employee);

        Set<SubQueryExpression<Integer>> queries = new HashSet<>();
        queries.add(query1);
        queries.add(query2);
        assertEquals(1, queries.size());
    }
}
