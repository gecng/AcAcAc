package com.h4h4da.letcode.l199

class TreeNode(val `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/**
 * LETCODE 199. 二叉树的右视图 水题
 * 求每一层最靠后的节点，可采用BFS 或者 DFS
 */
class Solution {
    /**
     * 采用BFS
     */
    fun rightSideView(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        var nodeList = mutableListOf<TreeNode>()
        if (root != null) {
            nodeList.add(root)
            list.add(root.`val`)
        }

        while (nodeList.isNotEmpty()) {
            var tempList = mutableListOf<TreeNode>()
            nodeList.forEach { node ->
                node.left?.let { tempList.add(it) }
                node.right?.let { tempList.add(it) }
            }
            nodeList = tempList
            if (nodeList.isEmpty()) break
            list.add(nodeList.last().`val`)
        }
        return list
    }


    /**
     * 采用DFS
     */
    fun test(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        return traverse(list, root, 0).toList()
    }

    /**
     * 中序遍历
     * 每次遍历的时候，更新visibleList中 深度为deepth的节点
     */
    private fun traverse(visibleList: MutableList<Int>, node: TreeNode?, deepth: Int): MutableList<Int> {
        if (node == null) return visibleList
        if (deepth <= visibleList.size - 1) {
            visibleList[deepth] = node.`val`
        } else {
            visibleList.add(node.`val`)
        }
        traverse(visibleList, node.left, deepth + 1)
        traverse(visibleList, node.right, deepth + 1)
        return visibleList
    }
}



