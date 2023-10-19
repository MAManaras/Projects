#!/usr/bin/env python3
from flask import Flask
from flask import render_template
from flask import request
from flask import session
from flask import redirect
from flask_session import Session

import requests
from requests.auth import HTTPBasicAuth

from datetime import datetime

import json

app = Flask(__name__)
# WARNING: Side effect of this is saving the password to disk, need to find a backend that is in-memory only
SESSION_TYPE = 'filesystem'
app.config.from_object(__name__)
Session(app)

SYSTEM_API = 'http://localhost:8080/dsa/api'

def authenticated():
    return 'username' in session

def authenticated_request(endpoint, data=None):
    post_headers = {
        'Content-Type': 'application/json'
    }
    if data == None:
        req = requests.get(SYSTEM_API + endpoint, auth=HTTPBasicAuth(session['username'], session['password']))
    else:
        req = requests.post(SYSTEM_API + endpoint, auth=HTTPBasicAuth(session['username'], session['password']), data=json.dumps(data), headers=post_headers)
    if req.status_code != 200:
        print('authenticated request returned: ' + str(req.status_code))
        #session.clear()
        return False
    return req.json()

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        req = requests.get(SYSTEM_API + '/user/me', auth=HTTPBasicAuth(username, password))
        if req.status_code != 200:
            print("Invalid username or password status code: " + str(req.status_code))
            return render_template('login.html.j2', error=True)
        session['username'] = username
        session['password'] = password
        return redirect('/', code=302)
    else:
        if authenticated():
            return redirect('/', code=302)
        return render_template('login.html.j2')

@app.route('/')
def index():
    if not authenticated():
        return redirect('/login', code=302)
    user = authenticated_request('/user/me')
    latestApplication = max(user['submittedApplications'], key=lambda a: a['id'], default=None)
    term = authenticated_request('/user/termInfo')
    print("Term: " + str(term))
    return render_template('index.html.j2', user=user, application=latestApplication, term=term)

@app.route('/profile', methods=['GET'])
def profile():
    if not authenticated():
        return redirect('/login', code=302)
    user = authenticated_request('/user/me')
    return render_template('profile.html.j2', user=user)

@app.route('/profile', methods=['POST'])
def update_profile():
    if not authenticated():
        return redirect('/login', code=302)
    print(authenticated_request('/user/contact', data=request.form ))
    user = authenticated_request('/user/me')
    return render_template('profile.html.j2', user=user)

@app.route('/logout')
def logout():
    session.clear()
    return redirect('/login', code=302)

@app.route('/apply', methods=['GET'])
def apply():
    if not authenticated():
        return redirect('/login', code=302)
    user = authenticated_request('/user/me')
    return render_template('apply.html.j2', user=user)

@app.route('/apply', methods=['POST'])
def send_application():
    if not authenticated():
        return redirect('/login', code=302)
    user = authenticated_request('/user/me')
    data = {
        "commuting": True if 'commuting' in request.form and request.form['commuting'] == 'on' else False,
        "studentSiblings": int(request.form['student_siblings']),
        "studentIncome": int(request.form['student_income']),
        "familyIncome": int(request.form['family_income']),
        "bothParentsUnemployed": True if 'both_parents_unemployed' in request.form and request.form['both_parents_unemployed'] == 'on' else False
    }
    print(authenticated_request('/user/submitApplication', data=data))
    return redirect('/', code=302)


@app.template_filter('ctime')
def timectime(s):
    return format(datetime.fromtimestamp(s/1000), '%a %d %B %Y')
