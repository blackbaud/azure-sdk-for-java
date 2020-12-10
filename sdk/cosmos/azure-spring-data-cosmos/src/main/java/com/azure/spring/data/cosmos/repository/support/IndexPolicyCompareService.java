package com.azure.spring.data.cosmos.repository.support;

import com.azure.cosmos.models.ExcludedPath;
import com.azure.cosmos.models.IncludedPath;
import com.azure.cosmos.models.IndexingPolicy;

import java.util.List;
import java.util.stream.Collectors;

public class IndexPolicyCompareService {

    public static boolean policyNeedsUpdate(IndexingPolicy existingPolicy, IndexingPolicy newPolicy) {
        return new IndexPolicyCompareService().needsUpdate(existingPolicy, newPolicy);
    }

    private boolean needsUpdate(IndexingPolicy existingPolicy, IndexingPolicy newPolicy) {
        return !hasSameIncludedPaths(existingPolicy.getIncludedPaths(), newPolicy.getIncludedPaths())
            || !hasSameExcludedPaths(existingPolicy.getExcludedPaths(), newPolicy.getExcludedPaths())
            || !existingPolicy.getIndexingMode().equals(newPolicy.getIndexingMode())
            || !existingPolicy.isAutomatic().equals(newPolicy.isAutomatic());
    }

    // Returns true if the lists are the same or the only difference is that the existing paths contain "/*"
    private boolean hasSameIncludedPaths(List<IncludedPath> existingPaths, List<IncludedPath> newPaths) {
        List<IncludedPath> existingListDiff = existingPaths.stream()
            .filter(element -> !newPaths.contains(element))
            .collect(Collectors.toList());

        List<IncludedPath> newListDiff = newPaths.stream()
            .filter(element -> !existingPaths.contains(element))
            .collect(Collectors.toList());

        return (existingListDiff.size() == 0 && newListDiff.size() == 0)
            || (newListDiff.size() == 0 && existingListDiff.size() == 1 && existingListDiff.get(0).getPath().equals("/*"));
    }

    // Returns true if the lists are the same or the only difference is that the existing paths contain the etag field
    private boolean hasSameExcludedPaths(List<ExcludedPath> existingPaths, List<ExcludedPath> newPaths) {
        List<ExcludedPath> existingListDiff = existingPaths.stream()
            .filter(element -> !newPaths.contains(element))
            .collect(Collectors.toList());

        List<ExcludedPath> newListDiff = newPaths.stream()
            .filter(element -> !existingPaths.contains(element))
            .collect(Collectors.toList());

        return (existingListDiff.size() == 0 && newListDiff.size() == 0)
            || (newListDiff.size() == 0 && existingListDiff.size() == 1 && existingListDiff.get(0).getPath().equals("/\"_etag\"/?"));
    }


}
