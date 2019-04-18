package com.neosoft.neostoreapp.utils

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.neosoft.neostoreapp.view.adapter.CartItemAdapter

class RecyclerItemTouchHelper constructor(
    var dragDir: Int,
    var swipDir: Int,
    var touchListener: RecyclerItemTouchHelperListener
) : ItemTouchHelper.SimpleCallback(dragDir, swipDir) {
    var listener: RecyclerItemTouchHelperListener = touchListener

    interface RecyclerItemTouchHelperListener {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            val foregroundView: View = (viewHolder as CartItemAdapter.CartItemHolder).viewForeground
            getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDrawOver(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val foregroundView: View = (viewHolder as CartItemAdapter.CartItemHolder).viewForeground
        getDefaultUIUtil().onDrawOver(canvas, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val foregroundView: View = (viewHolder as CartItemAdapter.CartItemHolder).viewForeground
        getDefaultUIUtil().clearView(foregroundView)
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val foregroundView: View = (viewHolder as CartItemAdapter.CartItemHolder).viewForeground
        getDefaultUIUtil().onDraw(canvas, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

//    private RecyclerItemTouchHelperListener listener;
//
//    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
//        super(dragDirs, swipeDirs);
//        this.listener = listener;
//    }
//
//    @Override
//    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        return true;
//    }
//
//    @Override
//    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//        if (viewHolder != null) {
//            final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
//
//            getDefaultUIUtil().onSelected(foregroundView);
//        }
//    }
//
//    @Override
//    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
//        RecyclerView.ViewHolder viewHolder, float dX, float dY,
//        int actionState, boolean isCurrentlyActive) {
//        final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
//        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
//            actionState, isCurrentlyActive);
//    }
//
//    @Override
//    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
//        getDefaultUIUtil().clearView(foregroundView);
//    }
//
//    @Override
//    public void onChildDraw(Canvas c, RecyclerView recyclerView,
//        RecyclerView.ViewHolder viewHolder, float dX, float dY,
//        int actionState, boolean isCurrentlyActive) {
//        final View foregroundView = ((CartListAdapter.MyViewHolder) viewHolder).viewForeground;
//
//        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
//            actionState, isCurrentlyActive);
//    }
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
//    }
//
//    @Override
//    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
//        return super.convertToAbsoluteDirection(flags, layoutDirection);
//    }
//
//    public interface RecyclerItemTouchHelperListener {
//        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
//    }
}
