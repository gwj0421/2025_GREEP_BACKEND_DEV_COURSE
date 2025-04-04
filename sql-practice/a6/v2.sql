# a6 DB 삭제/생성/선택
drop database if exists a6;
create database a6;
use a6;


# 부서(홍보, 기획, IT)
create table dept
(
    dept_id   int unsigned auto_increment not null,
    dept_name varchar(10) unique          not null,
    primary key (dept_id)
);
insert into dept (dept_name)
values ('홍보'),
       ('기획'),
       ('IT');

# 사원(홍길동/홍보/5000만원, 홍길순/홍보/6000만원, 임꺽정/기획/4000만원)
## IT부서는 아직 사원이 없음
create table emp
(
    emp_id     int unsigned not null auto_increment,
    emp_name   varchar(10)  not null,
    dept_id    int unsigned not null,
    emp_salary int unsigned not null comment '만원 단위',
    primary key (emp_id),
    constraint fk_dept_id foreign key (dept_id) references dept (dept_id)
);
insert into emp (emp_name, dept_id, emp_salary)
values ('홍길동', (select dept_id from dept where dept_name = '홍보' limit 1), 5000),
       ('홍길순', (select dept_id from dept where dept_name = '홍보' limit 1), 6000),
       ('임꺽정', (select dept_id from dept where dept_name = '기획' limit 1), 4000);

insert into emp (emp_name, dept_id, emp_salary)
values ('조건웅', (select dept_id from dept where dept_name = 'IT'), 3500);

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력
select d.dept_name '부서명', e.emp_id '사원번호', e.emp_name '사원명'
from emp e
         join dept d on e.dept_id = d.dept_id;

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력
## IT부서는 [IT, NULL, NULL] 으로 출력
select d.dept_name '부서명', if(d.dept_name = 'IT', null, e.emp_id), if(d.dept_name = 'IT', null, e.emp_name)
from emp e
         join dept d on e.dept_id = d.dept_id;

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력
## IT부서는 [IT, 0, -] 으로 출력
select d.dept_name '부서명', if(d.dept_name = 'IT', 0, e.emp_id), if(d.dept_name = 'IT', '-', e.emp_name)
from emp e
         join dept d on e.dept_id = d.dept_id;

# 모든 부서별, 최고연봉, IT부서는 0원으로 표시
select d.dept_name '부서명', if(d.dept_name = 'IT', '0원', concat(max(e.emp_salary), '원')) as '최고연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by d.dept_id;

# 모든 부서별, 최저연봉, IT부서는 0원으로 표시
select d.dept_name '부서명', if(d.dept_name = 'IT', '0원', concat(min(e.emp_salary), '원')) as '최고연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by d.dept_id;

# 모든 부서별, 평균연봉, IT부서는 0원으로 표시
select d.dept_name                                                                       '부서명',
        if(d.dept_name = 'IT', '0원', concat(cast(avg(e.emp_salary) as unsigned), '원')) as '최고연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by d.dept_id;

select d.dept_name                                                                                    '부서명',
        if(d.dept_name = 'IT', '0원', concat(cast((sum(e.emp_salary) / count(*)) as unsigned), '원')) as '최고연봉'
from emp e
         join dept d on e.dept_id = d.dept_id
group by d.dept_id;
