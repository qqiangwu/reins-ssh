package com.n2cj.util;

public final class Pager {
    private int mCurrentPage;
    private int mStartPage;
    private int mEndPage;
    private int mTotalPages;

    public Pager(final int itemCount, final int currentPage, final int itemPerPage) {
        mCurrentPage = currentPage;
        mTotalPages = 1 + itemCount / itemPerPage;

        final int pageSize = 4;
        final int pageNum = currentPage;
        final int PageNum_2 = (int) (pageSize % 2 == 0 ? Math.ceil(pageSize / 2) + 1 : Math.ceil(pageSize / 2));
        final int PageNum_3 = (int) (pageSize % 2 == 0 ? Math.ceil(pageSize / 2) : Math.ceil(pageSize / 2) + 1);

        if (pageSize >= mTotalPages) {
            mStartPage = 0;
            mEndPage = mTotalPages - 1;
        } else if (pageNum < PageNum_2) {
            mStartPage = 0;
            mEndPage = mTotalPages - 1 > pageSize ? pageSize : mTotalPages - 1;
        } else {
            mStartPage = pageNum + PageNum_3 >= mTotalPages ? mTotalPages - pageSize - 1 : pageNum - PageNum_2 + 1;
            int t = mStartPage + pageSize;
            mEndPage = t > mTotalPages ? mTotalPages - 1 : t;
        }

        if (mStartPage < 0) {
            mStartPage = 0;
            mEndPage = pageSize;
        }
        if (mEndPage < 0) {
            mEndPage = 0;
        }
    }

    public int pageCount() {
        return mTotalPages;
    }

    public int currentPage() {
        return mCurrentPage;
    }

    public int startPage() {
        return mStartPage;
    }

    public int endPage() {
        return mEndPage;
    }
}
