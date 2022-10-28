package com.giousa.spring.resolvabletype;

import java.util.List;

public class WrapperTest<T> {

	private String name;

	private List<Integer> ids;

	private List<? extends Number> names;

	private List<T> names2;
}
