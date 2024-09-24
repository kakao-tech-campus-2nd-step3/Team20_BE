package com.gamsa.activity.repository;

import com.gamsa.activity.domain.Activity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ActivityCustomRepository {

    Slice<Activity> findSlice(Pageable pageable);

}
