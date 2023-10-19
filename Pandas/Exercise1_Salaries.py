import pandas as pd

sal = pd.read_csv('Salaries.csv')
sal.head()
sal.info()                                  # 148654 Entries

print("")
# What is the average BasePay ?
print(sal['BasePay'].mean())

# What is the highest amount of OvertimePay in the dataset ?
sal['OvertimePay'].max()

#How much does JOSEPH DRISCOLL make (including benefits)?
print(sal[sal['EmployeeName']=='JOSEPH DRISCOLL']['TotalPayBenefits'])
print("")

#What is the name of highest paid person (including benefits)?
print(sal[sal['TotalPayBenefits'] == sal['TotalPayBenefits'].max()])

#What was the average (mean) BasePay of all employees per year? (2011-2014) ?
print(sal.groupby('Year').mean()['BasePay'])

#How many Job Titles were represented by only one person in 2013? (e.g. Job Titles with only one occurence in 2013?)
sum(sal[sal['Year'] == 2013]['JobTitle'].value_counts() == 1)

#How many people have the word Chief in their job title?
def chief_string(title):
    if 'chief' in title.lower():
        return True
    else:
        return False

sum(sal['JobTitle'].apply(lambda x: chief_string(x)))

#Is there a correlation between length of the Job Title string and Salary?
sal['title_len'] = sal['JobTitle'].apply(len)
sal[['title_len','TotalPayBenefits']].corr()




