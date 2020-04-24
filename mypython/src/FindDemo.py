# -*- encoding=utf-8 -*-

import pymysql

try:
    #开启连接
    conn = pymysql.connect(host='localhost', user='root', passwd='root', db='python', port=3306, charset='utf8')
    #打开游标
    cur = conn.cursor()
    # 执行sql
    sql = "select id,name,age from t1 where name like 'tom8%'"
    cur.execute(sql);
    all = cur.fetchall();
    for rec in all:
        print(str(rec[0]) + "," + rec[1] + "," + str(rec[2]))

    conn.commit()
    #
    cur.close()
    conn.close()

    conn.autocommit(False)

except Exception:
    print("发生异常")