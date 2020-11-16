package com.example.commoncenter.util.page;

public class PaginationHelper {


    /**
     * 构造分页对象
     *
     * @param totalSize 总条数
     * @param pageSize 每页条数
     * @return
     * @throws Exception
     */
    public static Pagination make(int totalSize, int pageNo, int pageSize) {
        int totalPage = calcTotalPage(totalSize, pageSize);
        return Pagination.builder()
                .pn(pageNo)
                .limit(pageSize)
                .totalPage(totalPage)
                .totalItem(totalSize).build();
    }

    public static int calcStart(int pageNo, int pageSize) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        return (pageNo - 1) * pageSize;
    }


    public static int ajustPageNo(int pageNo, int totalSize, int pageSize) {
        if (pageNo < 2) {
            return 1;
        } else {
            int totalPage = calcTotalPage(totalSize, pageSize);
            if (pageNo > totalPage) {
                return totalPage;
            }
        }
        return pageNo;
    }

    /**
     * 计算总页数
     *
     * @param totalSize 总条数
     * @param pageSize 每页条数
     * @return
     */
    private static int calcTotalPage(int totalSize, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("分页pageSize必须大于零");
        }
        // 总页码
        int totalPage = 0;
        if (totalSize > 0) {
            totalPage = totalSize / pageSize;
            if (totalSize % pageSize != 0) {
                totalPage++;
            }
        }
        return totalPage;
    }
}
