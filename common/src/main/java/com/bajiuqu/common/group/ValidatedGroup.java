package com.bajiuqu.common.group;

import javax.validation.groups.Default;

/**
 * @author 王瑞敏
 * @date 2022-07-18
 */
public class ValidatedGroup {

    public interface Insert extends Default {
    }

    public interface Delete extends Default {
    }

    public interface Update extends Default {
    }

    public interface Query extends Default {
    }

}
