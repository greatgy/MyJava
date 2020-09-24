#### linux命令：
    top  
    ps -ef  
    ifconfig
    netstat查看网络连接情况 netstat -ntlp | grep [port] 查看端口占用
    grep -o 'targetStr' filename | wc -l  (grep -o只输出匹配的字符串   wc -l统计行数 )
    df 查看磁盘空间    du 查看目录文件大小  free查看内存

    tar -zcvf 123.tar.gz  ./123  (-z gzip属性   -c打包 -x解包  -v显示过程  -f包文件名 )
    
    
    awk -F '分隔符' ‘print $1,$2’ x.log | sort -k 3   
    sort -k指定按照某列排序
         -r降序
         -u去重
    
    
