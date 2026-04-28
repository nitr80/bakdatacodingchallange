package com.egeozturk.bakdatacodingchallenge.services.interfaces;

import java.util.List;
import java.util.Map;

import com.egeozturk.bakdatacodingchallenge.models.interfaces.INode;

public interface ISqlService {
    List<Map<String, Object>> executeQueryFromNode(INode node);
}
