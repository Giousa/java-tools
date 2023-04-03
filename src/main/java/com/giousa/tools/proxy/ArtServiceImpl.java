package com.giousa.tools.proxy;

public class ArtServiceImpl implements IArtService{

    @Override
    public void work(String name) {
        System.out.println(name+ ":  ArtServiceImpl work!");
    }
}
