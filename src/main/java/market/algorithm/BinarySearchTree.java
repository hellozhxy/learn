package market.algorithm;

/**
  * @ClassName: BinarySearchTree
  * @Description: 二叉搜索树练习
  * @author market-zhongxy
  * @date 2016年7月25日 上午10:57:11
  *
 */
public class BinarySearchTree {

  public static TreeNode root;
  
  public BinarySearchTree(){
    this.root = null;
  }
  
  /*
   * 查找
   * 树深(N) O(lgN)
   * 1.从root节点开始
   * 2.比当前节点值小，则找其左节点
   * 3.比当前节点值大，则找其右节点
   * 4.与当前节点值相等，返回
   * 5.查找完毕返回null
   */
  public TreeNode search(int key){
    TreeNode current = root;
    while(current != null && key != current.value){
      if(key < current.value){
        current = current.left;
      } else{
        current = current.right;
      }
    }
    return current;
  }
  
  /*
   * 插入操作
   * 1.从root节点开始
   * 2.如果root为空，root为插入值
   * 
   * 循环：
   * 3.如果当前节点值大于插入值，找左节点
   * 4.如果当前节点值小于插入值，找右节点
   */
  public TreeNode insert(int key){
    //新增节点
    TreeNode newNode = new TreeNode(key);
    //当前节点
    TreeNode current = root;
    //上个节点
    TreeNode parent = null;
    if(current == null){
      root = newNode;
      return newNode;
    }
    while(true){
      parent = current;
      if(key < current.value){
        current = current.left;
        if(current == null){
          parent.left = newNode;
          return newNode;
        }
      } else{
        current = current.right;
        if(current == null){
          parent.right = newNode;
          return newNode;
        }
      }
    }
  }
  
  /*
   * 删除节点
   * 1.找到删除节点
   * 2.如果删除节点左节点为空，右节点也为空
   * 3.如果删除节点只有一个子节点、右节点或者左节点
   * 4.如果删除节点左右节点都不为空
   */
  public TreeNode delete(int key){
    TreeNode parent = root;
    TreeNode current = root;
    boolean isLeftChild = false;
    
    while(current.value != key){
      parent = current;
      if(current.value > key){
        isLeftChild = true;
        current = current.left;
      } else{
        isLeftChild = false;
        current = current.right;
      }
      
      if(current == null){
        return current;
      }
    }
    
    if(current.left == null && current.right == null){
      if(current == root){
        root = null;
      }
      
      if(isLeftChild){
        parent.left = null;
      } else {
        parent.right = null;
      }
    } else if(current.right == null){
      if(current == root){
        root = current.left;
      } else if(isLeftChild){
        parent.left = current.left;
      } else{
        parent.right = current.left;
      }
    } else if(current.left == null){
      if(current == root){
        root = current.right;
      }else if(isLeftChild){
        parent.left = current.right;
      }else{
        parent.right = current.right;
      }
    } else if(current.left != null && current.right != null){
      TreeNode successor = getDeleteSuccessor(current);
      if(current == root){
        root = successor;
      } else if(isLeftChild){
        parent.left = successor;
      } else {
        parent.right = successor;
      }
      
      successor.left = current.left;
    }
    
    return current;
  }

  public TreeNode getDeleteSuccessor(TreeNode deleteNode) {
    // TODO Auto-generated method stub
    TreeNode successor = null;
    TreeNode successorParent = null;
    TreeNode current = deleteNode.right;
    
    while(current != null){
      successorParent = successor;
      successor = current;
      current = current.left;
    }
    return successor;
  }
  
}
