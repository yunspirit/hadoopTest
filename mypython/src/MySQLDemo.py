# -*- encoding=utf-8 -*-

import pymysql
print("hello world")

try:
    conn = pymysql.connect(host='localhost', user='root', passwd='root', db='python', port=3306, charset='utf8')
    cur = conn.cursor()
    cur.execute('select version()')
    version = cur.fetchone()
    print(version)
    cur.close()
    conn.close()
except  Exception:
    print("发生异常")