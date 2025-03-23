set sql_safe_updates = 0;

# a5 데이터베이스 삭제/생성/선택
drop database if exists a5;

create database a5;
use a5;

# 부서(dept) 테이블 생성 및 홍보부서 기획부서 추가
create table dept
(
    dept_id   int unsigned unique auto_increment not null,
    dept_name varchar(10)                        not null,
    primary key (dept_id)
);

insert into dept (dept_id, dept_name)
values (1, '홍보');
insert into dept (dept_id, dept_name)
values (2, '기획');

# 사원(emp) 테이블 생성 및 홍길동사원(홍보부서), 홍길순사원(홍보부서), 임꺽정사원(기획부서) 추가
create table emp
(
    emp_id    int unsigned unique auto_increment not null,
    emp_name  varchar(15),
    dept_name varchar(10)                        not null,
    primary key (emp_id)
);

insert into emp (emp_name, dept_name)
values ('홍길동', '홍보'),
       ('홍길순', '홍보'),
       ('임꺽정', '기획');

# 홍보를 마케팅으로 변경
update dept
set dept_name = '마케팅'
where dept_name = '홍보';
update emp
set dept_name = '마케팅'
where dept_name = '홍보';

# 마케팅을 홍보로 변경
update dept
set dept_name = '홍보'
where dept_name = '마케팅';
update emp
set dept_name = '홍보'
where dept_name = '마케팅';

# 홍보를 마케팅으로 변경
update dept
set dept_name = '마케팅'
where dept_name = '홍보';
update emp
set dept_name = '마케팅'
where dept_name = '홍보';

# 구조를 변경하기로 결정(사원 테이블에서, 이제는 부서를 이름이 아닌 번호로 기억)
alter table emp
    add column dept_id int unsigned;

update emp e join dept d on e.dept_name = d.dept_name
    set e.dept_id=d.dept_id;
alter table emp
drop column dept_name;

alter table emp
    modify column dept_id int unsigned not null;

# 사장님께 드릴 인명록을 생성
select *
from emp;

# 사장님께서 부서번호가 아니라 부서명을 알고 싶어하신다.
# 그래서 dept 테이블 조회법을 알려드리고 혼이 났다.
select *
from dept;


# 사장님께 드릴 인명록을 생성(v2, 부서명 포함, ON 없이)
# 이상한 데이터가 생성되어서 혼남
select *
from emp,
     dept;

# 사장님께 드릴 인명록을 생성(v3, 부서명 포함, 올바른 조인 룰(ON) 적용)
# 보고용으로 좀 더 편하게 보여지도록 고쳐야 한다고 지적받음
select e.emp_name, d.dept_name
from emp e
         join dept d on e.dept_id = d.dept_id;

# 사장님께 드릴 인명록을 생성(v4, 사장님께서 보시기에 편한 칼럼명(AS))
select e.emp_name '이름', d.dept_name '부서명'
from emp e
         join dept d on e.dept_id = d.dept_id;