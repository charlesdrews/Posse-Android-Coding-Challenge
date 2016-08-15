package com.charlesdrews.charlesdrewsdemoapp.peoplelist.interfaces;

/**
 * Allow one RecyclerView adapter to work with multiple ViewHolder implementations, as long
 * as each implements this interface.
 *
 * Created by charlie on 8/13/16.
 */
public interface DataBinder<T> {
    void bindData(T data);
}
