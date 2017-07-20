モチベーションを上げるTodoListApp
====

## 概要
#### トップ画面
- Todoリストを追加できます
- Todoリストのタイトルをクリックすることで詳細を見ることができます  
<img src="https://github.com/utgwn/TodoListApp/blob/master/screenshot/screenshot1.png" width="400">

#### Todoリスト詳細画面
- Todoの追加ができます
- 残りのTodoの数を表示します  
<img src="https://github.com/utgwn/TodoListApp/blob/master/screenshot/screenshot3.png" width="400">

- finishボタンを押すとTodoを完了できます
- Todoを完了する度に褒めてくれます  
<img src="https://github.com/utgwn/TodoListApp/blob/master/screenshot/screenshot4.png" width="400">

#### Todo検索画面
<img src="https://github.com/utgwn/TodoListApp/blob/master/screenshot/screenshot5.png" width="400">

#### 褒め言葉詳細画面
- 好きな褒め言葉を追加できます
- Todoを完了すると、登録されたワードからランダムに１つ選んで褒めてくれます  
<img src="https://github.com/utgwn/TodoListApp/blob/master/screenshot/screenshot7.png" width="400">

## 使っているもの
- Java
- Spring Framework
- Spring JDBC
- PostgreSQL
- Maven

## 設計
MVCモデルのつもりです  
- データベースのそれぞれのテーブルに対応したDao、Entity、Serviceのクラスを作成
- 表示画面ごとにControllerクラスを作成
  - トップ画面に画面遷移する処理はHomeController、Todo詳細画面に画面遷移する処理はTodoController、みたいにした
  
#### データベース
- Todoリストテーブル
  - id serial PRIMARY KEY
  - title text NOT NULL
- Todoテーブル
  - id serial PRIMARY KEY
  - list_id int NOT NULL
  - detail text NOT NULL
- 褒め言葉テーブル
  - id serial PRIMARY KEY
  - message text NOT NULL

## 開発環境のセットアップ手順
- Java
  - JDK 8 とEclipse（Version: Neon.2 Release (4.6.2)）をインストール
- Spring Framework
  - Eclipseにspring tool suiteプラグインをインストール（Spring Tool Suite (STS) for Eclipse 3.8.3 RELEASE）
  - Spring Frameworkをプロジェクトのライブラリとして使用できるようにする  
    Eclipseで New → Spring Legacy Project  
    Templates:で「Simple Spring Web Maven」を選ぶ  
    プロジェクトを右クリックで Maven → Upgrade Project でアップグレード  
    プロジェクトを右クリック → Run As → 9 Maven install
- サーバ
  - [ここ](http://qiita.com/park-jh/items/08bb2541943f92e1feb1 "springの再入門 - eclipseでスタート")を参考にして Pivotal tc Server Developer Editionを設置
- データベース
  - PostgreSQLをインストール
  - spring-jdbc（4.3.6.RELEASE）をライブラリに追加
  - DBアクセスするためのDriverManagerDataSourceをmvc-config.xmlで定義
