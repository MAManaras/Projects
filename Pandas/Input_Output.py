import pandas as pd
import numpy as np
from sqlalchemy import create_engine


# CSV FILES
print(pd.read_csv("example"))

df = pd.read_csv('example')
df.to_csv('My_output', index = False )
print(df)

pd.read_csv('My_output')


# EXCEL FILES

df1 = pd.read_excel('Excel_Sample.xlsx', sheetname='Sheet1')
df1.to_excel('New_excel', sheet_name='Sheet1')



# SQL TABLES

engine = create_engine('sqlite:///:memory:')
df.to_sql('data', engine)
sql_df = pd.read_sql('data', con=engine)
sql_df






