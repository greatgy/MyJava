#### linux命令：
    top  
    ps -ef  
    ifconfig
    netstat查看网络连接情况 netstat -ntlp | grep [port] 查看端口占用
    grep -o 'targetStr' filename | wc -l  (grep -o只输出匹配的字符串   wc -l统计行数 )
        -c关键词数量
        -v不是关键词的
        -e
        
    df 查看磁盘空间    du 查看目录文件大小  free查看内存

    tar -zcvf 123.tar.gz  ./123  (-z gzip属性   -c打包 -x解包  -v显示过程  -f包文件名 )
    
    
    awk -F '分隔符' ‘print $1,$2’ x.log | sort -k 3   
    awk -F '{if($5=="1") {print $2}}'| sort | uniq -c | sort -r （去重是针对相邻的行，先排序再去重）
    
    sort -k指定按照某列排序
         -r降序
         -u去重
    uniq: -c重复的数量
          -u出现一次的
          -d出现多次的
    
    
