package com.yu.tree;


public class TrieNode {
    public int pass; //在加前缀树的时候 这个节点到过多少次
    public int end; //这个节点是否是字符串的结尾节点 如果是  是多少个字符串的结尾节点
    public TrieNode[] nexts;

    public TrieNode(){
        pass = 0;
        end = 0;
        nexts = new TrieNode[26];
        //HashMap<Character , TrieNode> nexts; (字符种类很多的话可以用哈希表)
        //TreeMap<Character , TrieTree> nexts; 有序
    }
    public static class Trie{
        private TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void insert(String word){
            if (word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass++;//根
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();//没有就新建
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        //word加入过几次
        public int search(String word){
            if (word == null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null){//找着找着发现下边没路了  也就是说字符还没结束 但是node已经没节点了
                    return 0;
                }
                node = node.nexts[index];//有 node后移
            }
            return node.end;
        }

        //所有加入的字符中 有几个是以pre为前缀的
        public int prefixNumber(String pre){
            if (pre == null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }

        public void delete(String word){
            if (search(word) != 0 ){
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                node.pass--;//根节点先--
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (--node.nexts[index].pass == 0){ //当前节点的下一节点的pass在--后为0  直接返回空
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;//找到最后  end--;
            }
        }
    }
}
