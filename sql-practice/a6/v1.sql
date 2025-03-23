# a6 DB 삭제/생성/선택
drop database if exists a6;
create database a6;
use a6;

# 부서(홍보, 기획, IT)
create table dept
(
    dept_id   int unsigned auto_increment not null,
    dept_name varchar(10)                 not null,
    primary key (dept_id)
);

insert into dept (dept_name)
values ('홍보'),
       ('기획'),
       ('IT');


# 사원(홍길동/홍보/5000만원, 홍길순/홍보/6000만원, 임꺽정/기획/4000만원)
create table emp
(
    emp_id     int unsigned auto_increment not null,
    emp_name   varchar(10)                 not null,
    emp_salary int unsigned                not null comment '만원 단위',
    dept_id    int unsigned,
    constraint fk_dept foreign key (dept_id) references dept (dept_id),
    primary key (emp_id)
);

# DETERMINISTIC => 입력값에 대하여 리턴값이 일정함을 명시
# BEGIN & END => 함수 본문의 시작과 끝
# DECLARE => 함수 내에 사용할 변수 선언
# SELECT ~ INTO => ~변수에 쿼리의 결과를 저장
create function getDeptId(dept_name varchar(10)) returns int
    deterministic
begin
    declare dept_id int;
select d.dept_id
into dept_id
from dept d
where d.dept_name = dept_name
    limit 1;
return dept_id;
end;

insert into emp (emp.emp_name, emp_salary, dept_id)
values ('홍길동', 5000, getDeptId('홍보')),
       ('홍길순', 6000, getDeptId('홍보')),
       ('임꺽정', 4000, getDeptId('기획'));

## IT부서는 아직 사원이 없음

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력
select d.dept_name '부서명', e.emp_id '사원번호', e.emp_name '사원명'
from emp e
         join dept d on e.dept_id = d.dept_id;

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력
## IT부서는 [IT, NULL, NULL] 으로 출력
insert into emp (emp_name, emp_salary, dept_id)
values ('조건웅', 3500, getDeptId('IT'));

select d.dept_name                              '부서명',
        if(d.dept_name = 'IT', null, e.emp_id)   '사원번호',
        if(d.dept_name = 'IT', null, e.emp_name) '사원명'
from emp e
         join dept d on e.dept_id = d.dept_id;

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력
## IT부서는 [IT, 0, -] 으로 출력
select d.dept_name                             '부서명',
        if(d.dept_name = 'IT', 0, e.emp_id)     '사원번호',
        if(d.dept_name = 'IT', '-', e.emp_name) '사원명'
from emp e
         join dept d on e.dept_id = d.dept_id;

# 모든 부서별, 최고연봉, IT부서는 0원으로 표시
select d.dept_name '부서명', if(d.dept_name = 'IT', 0, max(e.emp_salary)) '최고 연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by e.dept_id;

# 모든 부서별, 최저연봉, IT부서는 0원으로 표시
select d.dept_name '부서명', if(d.dept_name = 'IT', 0, min(e.emp_salary)) '최저 연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by d.dept_id;

# 모든 부서별, 평균연봉, IT부서는 0원으로 표시
# 방법 1
select d.dept_name '부서별', if(d.dept_name != 'IT', sum(e.emp_salary) / count(d.dept_id), 0) '평균 연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by d.dept_id;

# 방법2
select d.dept_name '부서명', if(d.dept_name = 'IT', 0, avg(e.emp_salary)) '평균 연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by d.dept_id;