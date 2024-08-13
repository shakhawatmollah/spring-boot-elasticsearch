package com.shakhawat.springbootelasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier() {
        return () -> Query.of(q -> q.matchAll(matchAllQuery()));
    }

    public static MatchAllQuery matchAllQuery() {
        val matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static Supplier<Query> supplierWithNameField(String fieldValue) {
        return () -> Query.of(q -> q.match(matchQueryWithNameField(fieldValue)));
    }

    public static MatchQuery matchQueryWithNameField(String fieldValue) {
        val matchQuery = new MatchQuery.Builder();
        return matchQuery.field("name").query(fieldValue).build();
    }

    public static Supplier<Query> createSupplierAutoSuggest(String partialProductName) {
        return () -> Query.of(q -> q.match(createAutoSuggestMatchQuery(partialProductName)));
    }

    public static MatchQuery createAutoSuggestMatchQuery(String partialProductName) {
        val autoSuggestQuery = new MatchQuery.Builder();
        return autoSuggestQuery.field("name").query(partialProductName).analyzer("standard").build();

    }

    public static Supplier<Query> supplierQueryForBoolQuery(String productName, Integer qty) {
        return () -> Query.of(q -> q.bool(boolQuery(productName, qty)));
    }

    public static BoolQuery boolQuery(String productName, Integer qty) {
        val boolQuery = new BoolQuery.Builder();
        return boolQuery.filter(termQuery(productName)).must(matchQuery(qty)).build();
    }

    public static List<Query> termQuery(String productName) {
        final List<Query> terms = new ArrayList<>();
        val termQuery = new TermQuery.Builder();
        terms.add(Query.of(q -> q.term(termQuery.field("name").value(productName).build())));
        return terms;
    }

    public static List<Query> matchQuery(Integer qty) {
        final List<Query> matches = new ArrayList<>();
        val matchQuery = new MatchQuery.Builder();
        matches.add(Query.of(q -> q.match(matchQuery.field("quantity").query(qty).build())));
        return matches;
    }

}
