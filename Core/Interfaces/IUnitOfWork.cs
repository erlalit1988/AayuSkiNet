using Core.Entities;
using System;
using System.Threading.Tasks;

namespace Core.Interfaces
{
    public interface IUnitOfWork :IDisposable
    {
        IGenericRepository<TEntity> Repository<TEntity>() where TEntity : BaseEntity;
        Task<bool> CompleteAsync(); // Save changes
        void Dispose(); // Dispose resources
    }
}
